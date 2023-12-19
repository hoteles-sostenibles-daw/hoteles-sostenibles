package com.tfc.daw.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reserva")
@Data
public class ReservaModel {
    @Id
    private String codigo;
    @Column
    private Date fechaEntrada;
    @Column
    private Date fechaSalida;
    @Column
    private String check_in;
    @Column
    private String check_out;
    @Column
    private int numeroHuespedes;
    @Column
    private String hotelNombre;
    @Column
    private String huespedDni;
    @Column
    private String habitacionNumero;
}
