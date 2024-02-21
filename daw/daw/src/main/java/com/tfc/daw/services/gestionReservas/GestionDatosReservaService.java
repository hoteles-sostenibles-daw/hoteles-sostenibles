package com.tfc.daw.services.gestionReservas;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfc.daw.emailHandler.EmailDetails;
import com.tfc.daw.emailHandler.EmailService;
import com.tfc.daw.models.DatosEntradaSalidaDTO;
import com.tfc.daw.models.HuespedModel;
import com.tfc.daw.models.ReservaModel;
import com.tfc.daw.repositories.webHotel.HuespedRepository;
import com.tfc.daw.repositories.webHotel.ReservaRepository;
import org.springframework.web.multipart.MultipartFile;

@Service

public class GestionDatosReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private HuespedRepository huespedRepository;
      @Autowired
    private EmailService emailService;

    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaEntrada(String fecha) {

        ArrayList<DatosEntradaSalidaDTO> listaEntradasFront = new ArrayList<>();

        ArrayList<ReservaModel> listaEntradas = this.reservaRepository.buscarFechaEntrada(fecha);
        if (listaEntradas.size() > 0) {
            for (ReservaModel reserva : listaEntradas) {
                DatosEntradaSalidaDTO datos = new DatosEntradaSalidaDTO();
                datos.setCodigoReserva(reserva.getCodigo());
                datos.setDni(reserva.getHuesped_dni());
                datos.setCheckIn(reserva.getCheck_in());
                listaEntradasFront.add(datos);
            }
        }

        return listaEntradasFront;

    }

    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaSalida(String fecha) {

        ArrayList<DatosEntradaSalidaDTO> listaSalidasFront = new ArrayList<>();

        ArrayList<ReservaModel> listaSalidas = this.reservaRepository.buscarFechaSalida(fecha);
        if (listaSalidas.size() > 0) {
            for (ReservaModel reserva : listaSalidas) {
                DatosEntradaSalidaDTO datos = new DatosEntradaSalidaDTO();
                datos.setCodigoReserva(reserva.getCodigo());
                datos.setDni(reserva.getHuesped_dni());
                datos.setCheckIn(reserva.getCheck_in());
                listaSalidasFront.add(datos);
            }
        }

        return listaSalidasFront;
    }

    private String obtenerEmail(String dni){
        Optional<HuespedModel> huesped = this.huespedRepository.findById(dni);
        return huesped.get().getEmail();
    }

    private String convertirFecha(){
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("d-M-yyyy");
        String fechaFormateada = formato.format(fechaActual);
        return fechaFormateada;
    }

    public String actualizarCheckIn(String codigoReserva){
        Optional<ReservaModel> reserva = obtenerReservaPorId(codigoReserva);
        if(reserva.isPresent()){
            if(convertirFecha().equals(reserva.get().getFecha_entrada()) && reserva.get().getCheck_in().equals("N")){
                this.reservaRepository.save(deOptionalAObjeto(reserva, "in"));
                this.emailService.sendEmailLanding(envioGuiaPractica(obtenerEmail(reserva.get().getHuesped_dni())));
                return "Checkin realizado correctamente. No se olvide de enviar la foto del DNI";
            }
            else{
                return "La fecha de la reserva no es válida. Debe hacer el checkin el día " + reserva.get().getFecha_entrada();
            }   
        }
        return "El código de reserva no existe"; 
    } 

    public void actualizarCheckOut(String codigoReserva){
        Optional<ReservaModel> reserva = obtenerReservaPorId(codigoReserva);
        if(reserva.isPresent()){
            this.reservaRepository.save(deOptionalAObjeto(reserva, "out"));
        }
    } 
    private EmailDetails envioGuiaPractica(String emailHuesped){
        EmailDetails email = new EmailDetails();
        email.setRecipient(emailHuesped);
        email.setSubject("Guía de buenas prácticas en sostenibilidad");
        email.setMsgBody("Enlace a la guía" );
        return email;
    }

    private Optional<ReservaModel> obtenerReservaPorId(String codigoReserva){
        Optional<ReservaModel> reserva = this.reservaRepository.findById(codigoReserva);
        return reserva;
    }

    private ReservaModel deOptionalAObjeto(Optional<ReservaModel> reserva, String tipoCheck){
        ReservaModel reservaObjeto= new ReservaModel();
        reservaObjeto.setCodigo(reserva.get().getCodigo());
        reservaObjeto.setFecha_entrada(reserva.get().getFecha_entrada());
        reservaObjeto.setFecha_salida(reserva.get().getFecha_salida());
        if(tipoCheck.equals("in")) {
            reservaObjeto.setCheck_in("S");
        } else {
            reservaObjeto.setCheck_in(reserva.get().getCheck_in());
        }
        if(tipoCheck.equals("out")) {
            reservaObjeto.setCheck_out("S");
        } else {
            reservaObjeto.setCheck_out(reserva.get().getCheck_out());
        }
        reservaObjeto.setNumero_huespedes(reserva.get().getNumero_huespedes());
        reservaObjeto.setHotel_nombre(reserva.get().getHotel_nombre());
        reservaObjeto.setHuesped_dni(reserva.get().getHuesped_dni());
        reservaObjeto.setHabitacion_numero(reserva.get().getHabitacion_numero());
        System.out.println(reservaObjeto);
        return reservaObjeto;
    }

    public Boolean obtenerInfoReserva(String codigoReserva) {

        if(this.reservaRepository.findById(codigoReserva).isPresent())
        {
            return true;
        }
        return false;
    }
    public Optional<ReservaModel> cargarInfoReserva(String codigoReserva) {
        return this.reservaRepository.findById(codigoReserva);
    }

    public Optional<HuespedModel> cargarHuespedReserva(String codigoReserva) {
        Optional<ReservaModel> reserva = this.reservaRepository.findById(codigoReserva);
        return this.huespedRepository.findById(reserva.get().getHuesped_dni());
    }

    public void guardarImagen(MultipartFile imagen, String dni) throws IOException {
        byte[] imagenByte = imagen.getBytes();
        Optional<HuespedModel> huesped = this.huespedRepository.findById(dni);
        this.huespedRepository.save(deOptionalAHuesped(huesped, imagenByte));
    }
    private HuespedModel deOptionalAHuesped(Optional<HuespedModel> huesped, byte[] imagen){
        HuespedModel huespedObjeto= new HuespedModel();
        huespedObjeto.setDni(huesped.get().getDni());
        huespedObjeto.setEmail(huesped.get().getEmail());
        huespedObjeto.setTelefono(huesped.get().getTelefono());
        huespedObjeto.setPersona_contacto(huesped.get().getPersona_contacto());
        huespedObjeto.setImagen_dni(imagen);
        return huespedObjeto;
    }

    public byte[] cargarImagen(String dni) throws IOException {
        Optional<HuespedModel> huesped = this.huespedRepository.findById(dni);
        return huesped.get().getImagen_dni();
    }
}
