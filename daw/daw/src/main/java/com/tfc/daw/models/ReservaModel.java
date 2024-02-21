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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public int getNumero_huespedes() {
        return numero_huespedes;
    }

    public void setNumero_huespedes(int numero_huespedes) {
        this.numero_huespedes = numero_huespedes;
    }

    public String getHotel_nombre() {
        return hotel_nombre;
    }

    public void setHotel_nombre(String hotel_nombre) {
        this.hotel_nombre = hotel_nombre;
    }

    public String getHuesped_dni() {
        return huesped_dni;
    }

    public void setHuesped_dni(String huesped_dni) {
        this.huesped_dni = huesped_dni;
    }

    public String getHabitacion_numero() {
        return habitacion_numero;
    }

    public void setHabitacion_numero(String habitacion_numero) {
        this.habitacion_numero = habitacion_numero;
    }
}
