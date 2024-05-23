package com.example.neoris.service.impl;

import com.example.neoris.client.CuentaClient;
import com.example.neoris.client.MovimientoClient;
import com.example.neoris.dto.ClienteDTO;
import com.example.neoris.dto.CuentaDTO;
import com.example.neoris.dto.MovimientoDTO;
import com.example.neoris.dto.ReporteDTO;
import com.example.neoris.entity.Cliente;
import com.example.neoris.exception.cliente.ClienteNotFoundException;
import com.example.neoris.exception.cuenta.CuentaNotFoundException;
import com.example.neoris.repository.ClienteRepository;
import com.example.neoris.service.ReporteService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ClienteRepository clienteRepository;
    private final CuentaClient cuentaClient;
    private final MovimientoClient movimientoClient;

    @Override
    @SneakyThrows
    public  List<ReporteDTO> generarReportePorFecha(Integer clienteId, String fechaDesde, String fechaHasta) {

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new ClienteNotFoundException("Cliente con id " + clienteId + " no existe"));

        List<CuentaDTO> cuentasPorClienteList = cuentaClient.getCuentasByCliente(cliente.getClienteId());

        if(cuentasPorClienteList.isEmpty()){
            throw new CuentaNotFoundException("El cliente no tiene cuentas registradas");
        }

        List<ReporteDTO> reportList = new ArrayList<>();

        cuentasPorClienteList.forEach((cuenta) -> {
            String numeroCuenta = cuenta.getNumeroCuenta();

            List<MovimientoDTO> movimientos = movimientoClient.getMovimientosByCuentaAndDateRange(numeroCuenta, fechaDesde, fechaHasta);
            if(!movimientos.isEmpty()){
                movimientos.forEach((movimiento) -> {
                    ReporteDTO reportDTO = new ReporteDTO();

                    reportDTO.setFecha(movimiento.getFecha());
                    reportDTO.setCliente(cliente.getNombre());
                    reportDTO.setNumeroCuenta(numeroCuenta);
                    reportDTO.setTipo(cuenta.getTipo());
                    reportDTO.setSaldoInicial(cuenta.getSaldoInicial());
                    reportDTO.setEstado(cuenta.isEstado());
                    reportDTO.setMovimiento(movimiento.getValor());
                    reportDTO.setSaldoDisponible(movimiento.getSaldo());

                    reportList.add(reportDTO);
                });
            }
        });

        return reportList;
    }
}
