package com.tfc.daw.models;

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
    private String fecha_entrada;
    @Column
    private String fecha_salida;
    @Column
    private String check_in;
    @Column
    private String check_out;
    @Column
    private int numero_huespedes;
    @Column
    private String hotel_nombre;
    @Column
    private String huesped_dni;
    @Column
    private String habitacion_numero;
}
