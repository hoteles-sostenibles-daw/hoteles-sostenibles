package com.tfc.daw.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "habitacion")
@Data
public class HabitacionModel {
    @Id
    private int numero;
    @Column
    private String habitacion_ocupada;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getHabitacionOcupada() {
        return habitacion_ocupada;
    }

    public void setHabitacionOcupada(String habitacionOcupada) {
        this.habitacion_ocupada = habitacionOcupada;
    }
}
