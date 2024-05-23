package com.example.neoris.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="movimiento")
public class Movimiento {

    @Id
    @Column(name = "movimiento_id", unique = true, nullable = false)
    private String movimientoId;

    @Column(name="fecha")
    private String fecha;

    @Column(name="tipo_movimiento")
    private String tipoMovimiento;

    @Column(name="valor")
    private BigDecimal valor;

    @Column(name="saldo")
    private BigDecimal saldo;

    @Column(name="numero_cuenta")
    private String numeroCuenta;
}
