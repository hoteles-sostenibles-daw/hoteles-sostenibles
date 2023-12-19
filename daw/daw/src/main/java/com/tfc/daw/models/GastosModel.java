package com.tfc.daw.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gastos")
@Data
public class GastosModel {
    @Id
    private String id;
    @Column
    private String concepto;
    @Column
    private String pagado;
    @Column
    private int precio;
    @Column
    private int habitacionNumero;

}
