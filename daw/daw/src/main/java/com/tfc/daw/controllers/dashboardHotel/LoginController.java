package com.tfc.daw.controllers.dashboardHotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfc.daw.models.DatosLogin;
import com.tfc.daw.services.gestionReservas.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public Resource cargarPaginaLogin() {
        return new ClassPathResource("/static/html/dashboardHotel/login/login.html");
    }

    @PostMapping("/login")
    public ResponseEntity<String> validarDatosUsuario(@RequestBody DatosLogin body, HttpServletRequest request) {
        
        if(this.loginService.validarDatosUsuario(body)) {
            HttpSession session = request.getSession(true);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }
}
