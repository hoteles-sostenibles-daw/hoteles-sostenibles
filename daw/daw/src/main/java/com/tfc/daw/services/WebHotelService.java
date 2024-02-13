package com.tfc.daw.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfc.daw.emailHandler.EmailDetails;
import com.tfc.daw.emailHandler.EmailService;
import com.tfc.daw.models.DatosFrontend;
import com.tfc.daw.models.HuespedModel;
import com.tfc.daw.models.ReservaModel;
import com.tfc.daw.repositories.webHotel.HuespedRepository;
import com.tfc.daw.repositories.webHotel.ReservaRepository;

@Service
public class WebHotelService {
    @Autowired
    private HuespedRepository huespedRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private EmailService emailService;
    

    public void gestionarDatosReserva(DatosFrontend body) {
        Optional<HuespedModel> huesped = this.huespedRepository.findById(body.getDni());
        if (!huesped.isPresent()) {
            this.huespedRepository.save(crearHuesped(body));
        }
        this.reservaRepository.save(crearReserva(body));
        this.emailService.sendEmailLanding(crearEmailInfoReserva(body));
    }

    private ReservaModel crearReserva(DatosFrontend body) {
        ReservaModel reserva = new ReservaModel();
        reserva.setCodigo(crearCodigo(body.getDni(), body.getFechaEntrada(), body.getFechaSalida()));
        reserva.setFecha_entrada(body.getFechaEntrada());
        reserva.setFecha_salida(body.getFechaSalida());
        reserva.setCheck_in("N");
        reserva.setCheck_out("N");
        reserva.setNumero_huespedes(body.getNumeroPersonas());
        reserva.setHotel_nombre("Hotel AC Balagares");
        reserva.setHuesped_dni(body.getDni());
        reserva.setHabitacion_numero(null);
        return reserva;
    }

    private String crearCodigo(String dni, String fechaEntrada, String fechaSalida) {
        String dniFormateado = dni.substring(0, dni.length() - 1) + dni.substring(dni.length() - 1).toLowerCase();
        String fechaEntradaFormateada = fechaEntrada.replace("-", "");
        String fechaSalidaFormateada = fechaSalida.replace("-", "");
        return dniFormateado + fechaEntradaFormateada + fechaSalidaFormateada;
    }

    private HuespedModel crearHuesped(DatosFrontend body) {
        HuespedModel huesped = new HuespedModel();
        huesped.setDni(body.getDni());
        huesped.setEmail(body.getEmail());
        huesped.setTelefono(body.getTelefono());
        huesped.setPersona_contacto(body.getNombrePersona());
        return huesped;

    }

    private EmailDetails crearEmailInfoReserva(DatosFrontend body) {
        String codigo = crearCodigo(body.getDni(), body.getFechaEntrada(), body.getFechaSalida());
        EmailDetails email = new EmailDetails();
        email.setRecipient(body.getEmail());
        email.setSubject("Información de la reserva");
        email.setMsgBody("Nombre del hotel: Hotel AC Balagares " + "\n" +
                "Código de la reserva: " + codigo + "\n" +
                "DNI:" + body.getDni() + "\n" +
                "Fecha de Entrada: " + body.getFechaEntrada() + "\n" +
                "Fecha de Salida: " + body.getFechaSalida() + "\n" +
                "Número de personas: " + body.getNumeroPersonas() + "\n" +
                "Si prefiere hacer el check in online, pulse el siguiente enlace como mínimo 15 minutos antes de su llegada: http://localhost:8080/actualizarcheckin/"
                + codigo + "\n" +
                "Para finalizar su check in debe enviarnos una foto de su DNI como respuesta a este email" + "\n" +
                "Una vez realizados estos pasos recoja su llave en recepción"
                + "\n\n" +

                "Para más información póngase en contacto con hoteles.sostenibles.info@gmail.com o en el siguiente número de teléfono +34 6890005733  ");
        return email;

    }
}
