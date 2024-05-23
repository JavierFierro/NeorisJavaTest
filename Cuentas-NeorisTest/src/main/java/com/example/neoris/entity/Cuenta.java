package com.example.neoris.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cuenta")
public class Cuenta {

    @Id
    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;

    @Column(name = "estado")
    private boolean estado;

    @Column(name="cliente_id")
    private Integer clienteId;
}
