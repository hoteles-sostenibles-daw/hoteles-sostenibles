package com.tfc.daw.services.gestionReservas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfc.daw.models.ReservaModel;
import com.tfc.daw.repositories.webHotel.ReservaRepository;

@Service

public class GestionDatosReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public ArrayList<ReservaModel> obtenerDatosFecha(String fecha) {
        // nº de reserva
        // dni
        // check in
        ArrayList<ReservaModel> listaEntradas = this.reservaRepository.findByFecha_entrada(fecha);
        System.out.println(listaEntradas);
        return listaEntradas;
    }
}
