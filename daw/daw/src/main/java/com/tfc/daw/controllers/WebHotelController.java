package com.tfc.daw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfc.daw.models.DatosFrontend;
import com.tfc.daw.services.WebHotelService;

@RestController
public class WebHotelController {
    @Autowired
    private WebHotelService webHotelService;

    @GetMapping("/webhotel")
    public Resource gestionReservas() {
        return new ClassPathResource("/static/html/webhotel.html");
    }

    @PostMapping("/reserva")
    public void gestionarDatosReserva(@RequestBody DatosFrontend body) {
        this.webHotelService.gestionarDatosReserva(body);

    }

}
