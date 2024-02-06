package com.tfc.daw.repositories.webHotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfc.daw.models.ReservaModel;

import java.util.ArrayList;

public interface ReservaRepository extends JpaRepository<ReservaModel, String> {
    @Query(value = "SELECT * FROM reserva WHERE fecha_entrada=?", nativeQuery = true)
    public ArrayList<ReservaModel> findByFecha_entrada(String fecha_entrada);
}
