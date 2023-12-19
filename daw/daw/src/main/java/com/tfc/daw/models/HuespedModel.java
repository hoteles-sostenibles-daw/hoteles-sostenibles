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
    private String personaContacto;
}
