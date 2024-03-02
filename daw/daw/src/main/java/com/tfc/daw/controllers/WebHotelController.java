package com.tfc.daw.controllers;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> gestionarDatosReserva(@RequestBody DatosFrontend body) {
        if(Pattern.matches("^[a-zA-Z]+$", body.getNombrePersona()) && Pattern.matches("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$", body.getDni()) &&
        Pattern.matches("^[0-9]{9}$", body.getTelefono()) &&  Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", body.getEmail()) &&
        Pattern.matches("^[1-5]{1}$", Integer.toString(body.getNumeroPersonas())) && Pattern.matches("^(?:[1-9]|[12]\\d|3[01])-(?:[1-9]|1[0-2])-(?:202[4-9]|20[3-9]\\d|2[1-9]\\d{2}|[3-9]\\d{3}|[1-9]\\d{4})$", body.getFechaEntrada())
        && Pattern.matches("^(?:[1-9]|[12]\\d|3[01])-(?:[1-9]|1[0-2])-(?:202[4-9]|20[3-9]\\d|2[1-9]\\d{2}|[3-9]\\d{3}|[1-9]\\d{4})$", body.getFechaSalida())) {
            this.webHotelService.gestionarDatosReserva(body);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
