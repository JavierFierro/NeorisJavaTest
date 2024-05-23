package com.example.neoris.dto;

import com.example.neoris.entity.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends Persona implements Serializable {

    private Integer clienteId;
    private String contrasena;
    private boolean estado;
}
