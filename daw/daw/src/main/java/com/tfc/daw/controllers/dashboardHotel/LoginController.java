package com.tfc.daw.controllers.dashboardHotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfc.daw.models.DatosLogin;
import com.tfc.daw.services.WebHotelService;

@RestController
public class LoginController {
    @GetMapping("/login")
    public Resource cargarPaginaLogin() {
        return new ClassPathResource("/static/html/dashboardHotel/login/login.html");
    }

    @PostMapping("/login")
    public void validarDatosUsuario(@RequestBody DatosLogin body) {
        System.out.println(body);

    }
}
