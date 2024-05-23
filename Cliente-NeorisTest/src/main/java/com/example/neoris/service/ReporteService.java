package com.example.neoris.service;

import com.example.neoris.dto.ReporteDTO;

import java.util.List;

public interface ReporteService {
    public List<ReporteDTO> generarReportePorFecha(Integer clienteId, String fechaDesde, String fechaHasta);
}
