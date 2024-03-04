package com.tfc.daw.repositories.webHotel;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfc.daw.models.DatosLogin;

public interface LoginRepository extends JpaRepository<DatosLogin, String> {
    
}
