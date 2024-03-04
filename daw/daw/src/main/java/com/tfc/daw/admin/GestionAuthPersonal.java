package com.tfc.daw.admin;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfc.daw.models.DatosLogin;
import com.tfc.daw.repositories.webHotel.LoginRepository;

// @RestController
// public class GestionAuthPersonal {
//     @Autowired
//     private LoginRepository loginRepository;


//     @GetMapping("/authgen")
//     public void crearPersona() {

//         String hotel = "Hotel AC Balagares";
//         String recepcionPwd = BCrypt.hashpw("recepcion123", BCrypt.gensalt());
//         String serviciosPwd = BCrypt.hashpw("servicios123", BCrypt.gensalt());
//         DatosLogin recepcion = new DatosLogin("recepcion",recepcionPwd,hotel);
//         DatosLogin servicios = new DatosLogin("servicios",serviciosPwd,hotel);
        
//         this.loginRepository.save(recepcion);
//         this.loginRepository.save(servicios);
//     }
// }
