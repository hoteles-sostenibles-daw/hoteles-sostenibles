package com.tfc.daw.repositories.webHotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfc.daw.models.ReservaModel;

import java.util.ArrayList;

public interface ReservaRepository extends JpaRepository<ReservaModel, String> {

    @Query("SELECT r FROM ReservaModel r WHERE r.fecha_entrada = :fechaEntrada")
    public ArrayList<ReservaModel> buscarFechaEntrada(@Param("fechaEntrada") String fechaEntrada);

    @Query("SELECT r FROM ReservaModel r WHERE r.fecha_salida = :fechaSalida")
    public ArrayList<ReservaModel> buscarFechaSalida(@Param("fechaSalida") String fechaSalida);
}
