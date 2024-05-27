package com.tfc.daw.models;

public class DatosGastoFront {
    private String concepto;
    private String precio;
    private String numHabitacion;

    public DatosGastoFront() {}
    
    public DatosGastoFront(String concepto, String precio, String numHabitacion) {
        this.concepto = concepto;
        this.precio = precio;
        this.numHabitacion = numHabitacion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public String getNumHabitacion() {
        return numHabitacion;
    }
    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }
 
}
