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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getHabitacionNumero() {
        return habitacionNumero;
    }

    public void setHabitacionNumero(int habitacionNumero) {
        this.habitacionNumero = habitacionNumero;
    }
}
