package com.tfc.daw.controllers.dashboardHotel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;

import com.tfc.daw.models.DatosEntradaSalidaDTO;

import com.tfc.daw.services.gestionReservas.GestionDatosReservaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DashboardHotelController {
    @Autowired
    private GestionDatosReservaService gestionDatosReservaService;

    @GetMapping("/recepcion")
    public Resource gestionDeReservas() {
        return new ClassPathResource("/static/html/dashboardHotel/gestionReserva/dashboardGestionReservas.html");
    }

    @PostMapping("/fechaentrada")
    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaEntrada(@RequestBody String fecha) {

        return this.gestionDatosReservaService.obtenerFechaEntrada(fecha);

    }

    @PostMapping("/fechasalida")
    public ArrayList<DatosEntradaSalidaDTO> obtenerFechaSalida(@RequestBody String fecha) {

        return this.gestionDatosReservaService.obtenerFechaSalida(fecha);

    }

}