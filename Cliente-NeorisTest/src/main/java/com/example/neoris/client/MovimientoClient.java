package com.example.neoris.client;

import com.example.neoris.dto.MovimientoDTO;

import java.util.List;

public interface MovimientoClient {
    public List<MovimientoDTO> getMovimientosByCuentaAndDateRange(String numeroCuenta, String fechaDesde, String fechaHasta);
}
