package com.example.neoris.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
public class Persona {

    private String identificacion;
    private String nombre;
    private String genero;
    private String edad;
    private String direccion;
    private String telefono;
}
