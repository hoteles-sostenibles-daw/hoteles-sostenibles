package com.tfc.daw.repositories.webHotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfc.daw.models.HabitacionModel;

import jakarta.transaction.Transactional;

public interface HabitacionRepository extends JpaRepository<HabitacionModel, Integer>{

    @Transactional
    @Modifying
    @Query("UPDATE HabitacionModel h SET h.habitacion_ocupada = 'S' WHERE h.numero = :numero")
    void actualizarEstadoHabitacion(@Param("numero") int numero);

    @Transactional
    @Modifying
    @Query("UPDATE HabitacionModel h SET h.habitacion_ocupada = 'N' WHERE h.numero = :numero")
    void actualizarEstadoHabitacionSalida(@Param("numero") int numero);

}
