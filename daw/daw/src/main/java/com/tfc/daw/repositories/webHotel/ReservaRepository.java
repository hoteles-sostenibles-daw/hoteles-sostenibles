package com.tfc.daw.repositories.webHotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfc.daw.models.ReservaModel;

import jakarta.transaction.Transactional;

import java.util.ArrayList;

public interface ReservaRepository extends JpaRepository<ReservaModel, String> {

    @Query("SELECT r FROM ReservaModel r WHERE r.fecha_entrada = :fechaEntrada")
    public ArrayList<ReservaModel> buscarFechaEntrada(@Param("fechaEntrada") String fechaEntrada);

    @Query("SELECT r FROM ReservaModel r WHERE r.fecha_salida = :fechaSalida")
    public ArrayList<ReservaModel> buscarFechaSalida(@Param("fechaSalida") String fechaSalida);

    @Query("SELECT r.codigo FROM ReservaModel r WHERE r.habitacion_numero = :numHabitacion")
    public String obtenerCodigoReserva(@Param("numHabitacion") int numHabitacion);

    @Transactional
    @Modifying
    @Query("UPDATE ReservaModel r SET r.habitacion_numero = :habitacion WHERE r.codigo = :codigo")
    public void asignarHabitacion(@Param("codigo") String codigoReserva, @Param("habitacion") int numeroHabitacion);
}
