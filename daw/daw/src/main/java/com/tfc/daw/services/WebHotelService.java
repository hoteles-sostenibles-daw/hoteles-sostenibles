package com.tfc.daw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void gestionarDatosReserva(DatosFrontend body) {

        this.huespedRepository.save(crearHuesped(body));
        this.reservaRepository.save(crearReserva(body));

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
}
