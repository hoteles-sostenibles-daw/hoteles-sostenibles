package com.tfc.daw.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data
public class HotelModel {
    @Id
    private String nombre;
    @Column
    private int habitacionesTotales;
    @Column
    private int habitacionesOcupadas;
}
