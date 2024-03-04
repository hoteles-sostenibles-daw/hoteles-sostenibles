package com.tfc.daw.services.gestionReservas;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfc.daw.models.DatosLogin;
import com.tfc.daw.repositories.webHotel.LoginRepository;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;

    public boolean validarDatosUsuario(DatosLogin datos) {

        Optional<DatosLogin> trabajador = this.loginRepository.findById(datos.getRol());

        if(trabajador.isPresent()) {
            if(BCrypt.checkpw(datos.getContrasena(), trabajador.get().getContrasena())) {
                return true;
            }
        }
        return false;
    }
}
