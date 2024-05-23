package com.example.neoris.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO implements Serializable {

    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private boolean estado;
    private Integer clienteId;
}
