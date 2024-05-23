package com.example.neoris.service;

import com.example.neoris.dto.MovimientoDTO;
import com.example.neoris.entity.Movimiento;

import java.util.List;

public interface MovimientoService {
    public List<Movimiento> getAllMovimientos();
    public MovimientoDTO getMovimientoById(String movimientoId);
    public List<Movimiento> getMovimientosByNumeraCuentaAndDateRange(String numeroCuenta, String fechaDesde, String fechaHasta);
    public Movimiento saveMovimiento(MovimientoDTO movimientoDTO);
    public MovimientoDTO updateMovimiento(MovimientoDTO movimientoDTO);
    public void deleteMovimiento(String movimientoId);
}
