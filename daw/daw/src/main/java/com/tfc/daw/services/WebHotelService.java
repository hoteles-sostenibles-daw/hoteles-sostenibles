package com.tfc.daw.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void gestionarDatosReserva(DatosFrontend body) throws ParseException {
        // this.reservaRepository.save(crearReserva(body));
        // this.huespedRepository.save(crearHuesped(body));
        System.out.println(crearReserva(body));
    }

    private ReservaModel crearReserva(DatosFrontend body) throws ParseException {
        ReservaModel reserva = new ReservaModel();
        reserva.setCodigo(crearCodigo(body.getDni(), body.getFechaEntrada(), body.getFechaSalida()));
        reserva.setFechaEntrada(cambioFormatoFecha(body.getFechaEntrada()));
        reserva.setFechaSalida(cambioFormatoFecha(body.getFechaSalida()));
        reserva.setCheck_in("N");
        reserva.setCheck_out("N");
        reserva.setNumeroHuespedes(body.getNumeroPersonas());
        reserva.setHotelNombre("Hotel AC Balagares");
        reserva.setHuespedDni(body.getDni());
        reserva.setHabitacionNumero(null);
        return reserva;
    }

    private String crearCodigo(String dni, String fechaEntrada, String fechaSalida) {
        String dniFormateado = dni.substring(0, dni.length() - 1) + dni.substring(dni.length() - 1).toLowerCase();
        String fechaEntradaFormateada = fechaEntrada.replace("-", "");
        String fechaSalidaFormateada = fechaSalida.replace("-", "");
        return dniFormateado + fechaEntradaFormateada + fechaSalidaFormateada;
    }

    private Date cambioFormatoFecha(String fecha) throws ParseException {
        String dia = fecha.split("-")[0];
        String mes = fecha.split("-")[1];
        String year = fecha.split("-")[2];

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date cambioFecha = formato.parse(year + "-" + mes + "-" + dia);
        return cambioFecha;
    }

    private HuespedModel crearHuesped(DatosFrontend body) {
        HuespedModel huesped = new HuespedModel();
        huesped.setDni(body.getDni());
        huesped.setEmail(body.getEmail());
        huesped.setTelefono(body.getTelefono());
        huesped.setPersonaContacto(body.getNombrePersona());
        return huesped;

    }
}
