package com.tfc.daw.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "huesped")
@Data
public class HuespedModel {
    @Id
    private String dni;
    @Column
    private String email;
    @Column
    private String telefono;
    @Column
    private String persona_contacto;
    @Column
    private byte[] imagen_dni;

    public byte[] getImagen_dni() {
        return imagen_dni;
    }

    public void setImagen_dni(byte[] imagen_dni) {
        this.imagen_dni = imagen_dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPersona_contacto() {
        return persona_contacto;
    }

    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }
}
