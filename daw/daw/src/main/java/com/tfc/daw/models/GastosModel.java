package com.tfc.daw.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gastos")
@Data
public class GastosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String concepto;
    @Column
    private String pagado;
    @Column
    private float precio;
    @Column
    private String reserva_codigo;

    

    public GastosModel() {
    }

    public GastosModel( String concepto, String pagado, float precio, String reserva_codigo) {
    
        this.concepto = concepto;
        this.pagado = pagado;
        this.precio = precio;
        this.reserva_codigo = reserva_codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getReservaCodigo() {
        return reserva_codigo;
    }

    public void setReservaCodigo(String reserva_codigo) {
        this.reserva_codigo = reserva_codigo;
    }
}
