package com.tfc.daw.repositories.webHotel;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfc.daw.models.GastosModel;

import jakarta.transaction.Transactional;


public interface GastosRespository extends JpaRepository<GastosModel, Integer> {

    @Query("SELECT g FROM GastosModel g WHERE g.reserva_codigo = :numReserva")
    public ArrayList<GastosModel> obtenerGastosNumeroReserva(@Param("numReserva") String numReserva);

    @Transactional
    @Modifying
    @Query("UPDATE GastosModel g SET g.pagado = 'S' WHERE g.reserva_codigo = :numReserva")
    public int actualizarPago(@Param("numReserva") String numReserva);
}
