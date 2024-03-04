package com.tfc.daw.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosLogin {
    @Id
    private String rol;
    @Column
    private String contrasena;
    @Column
    private String hotel_nombre;



    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getHotel_nombre() {
        return hotel_nombre;
    }

    public void setHotel_nombre(String hotel_nombre) {
        this.hotel_nombre = hotel_nombre;
    }

}
