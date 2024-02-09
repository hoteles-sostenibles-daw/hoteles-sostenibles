package com.tfc.daw.services.gestionReservas;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfc.daw.models.DatosEntradaSalidaDTO;
import com.tfc.daw.models.ReservaModel;
import com.tfc.daw.repositories.webHotel.ReservaRepository;

@Service

public class GestionDatosReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

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

    public void actualizarCheckIn(String codigoReserva){
        Optional<ReservaModel> reserva = obtenerReservaPorId(codigoReserva);
        if(reserva.isPresent()){
            this.reservaRepository.save(deOptionalAObjeto(reserva));
        } 
    } 

    private Optional<ReservaModel> obtenerReservaPorId(String codigoReserva){
        Optional<ReservaModel> reserva = this.reservaRepository.findById(codigoReserva);
        return reserva;
    }

    private ReservaModel deOptionalAObjeto(Optional<ReservaModel> reserva){
        ReservaModel reservaObjeto= new ReservaModel();
        reservaObjeto.setCodigo(reserva.get().getCodigo());
        reservaObjeto.setFecha_entrada(reserva.get().getFecha_entrada());
        reservaObjeto.setFecha_salida(reserva.get().getFecha_salida());
        reservaObjeto.setCheck_in("S");
        reservaObjeto.setCheck_out(reserva.get().getCheck_out());
        reservaObjeto.setNumero_huespedes(reserva.get().getNumero_huespedes());
        reservaObjeto.setHotel_nombre(reserva.get().getHotel_nombre());
        reservaObjeto.setHuesped_dni(reserva.get().getHuesped_dni());
        reservaObjeto.setHabitacion_numero(reserva.get().getHabitacion_numero());
        System.out.println(reservaObjeto);
        return reservaObjeto;
    }
}
