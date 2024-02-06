package com.tfc.daw.controllers.dashboardHotel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfc.daw.models.DatosEntradaSalidaDTO;
import com.tfc.daw.models.ReservaModel;
import com.tfc.daw.services.gestionReservas.GestionDatosReservaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DashboardHotelController {
    @Autowired
    private GestionDatosReservaService gestionDatosReservaService;

    @PostMapping("/fechareserva")
    public ArrayList<ReservaModel> obtenerDatosFecha(@RequestBody String fecha) {

        return this.gestionDatosReservaService.obtenerDatosFecha(fecha);

    }

}