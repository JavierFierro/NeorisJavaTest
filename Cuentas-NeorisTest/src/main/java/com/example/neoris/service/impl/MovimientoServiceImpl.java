package com.example.neoris.service.impl;

import com.example.neoris.dto.MovimientoDTO;
import com.example.neoris.entity.Cuenta;
import com.example.neoris.entity.Movimiento;
import com.example.neoris.exception.cuenta.CuentaNotFoundException;
import com.example.neoris.exception.movimiento.InvalidMovimientoTypeException;
import com.example.neoris.exception.movimiento.MovimientoNotFoundException;
import com.example.neoris.exception.movimiento.SaldoNoDisponibleException;
import com.example.neoris.repository.CuentaRepository;
import com.example.neoris.repository.MovimientoRepository;
import com.example.neoris.service.MovimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    @SneakyThrows
    public MovimientoDTO getMovimientoById(String movimientoId) {

        Movimiento movimiento = movimientoRepository.findById(movimientoId).orElseThrow(
                () -> new MovimientoNotFoundException("Movimiento con id " + movimientoId + " no encontrado")
        );

        ObjectMapper mapper = new ObjectMapper();
        MovimientoDTO movimientoDTO = mapper.convertValue(movimiento, MovimientoDTO.class);

        return movimientoDTO;
    }

    @Override
    @SneakyThrows
    public List<Movimiento> getMovimientosByNumeraCuentaAndDateRange(String numeroCuenta, String fechaDesde, String fechaHasta) {
        List<Movimiento> movimientosListByCuentaSorted = movimientoRepository.findMovimientosBetweenDates(
                numeroCuenta, formatStringToDate(fechaDesde), formatStringToDate(fechaHasta));
        return movimientosListByCuentaSorted;
    }

    @Override
    @SneakyThrows
    public Movimiento saveMovimiento(MovimientoDTO movimientoDTO) {

        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(movimientoDTO.getNumeroCuenta());
        if(cuentaOpt.isEmpty()){
            throw new CuentaNotFoundException("No se puede registrar un movimiento a una cuenta inexistente");
        }

        ObjectMapper mapper = new ObjectMapper();
        Movimiento movimiento = mapper.convertValue(movimientoDTO, Movimiento.class);

        String uniqueKey = RandomStringUtils.randomNumeric(3);
        movimiento.setMovimientoId(uniqueKey);

        List<Movimiento> movimientosListByCuentaSorted = movimientoRepository.findByNumeroCuentaOrderByFechaDesc(movimiento.getNumeroCuenta());

        if(movimientosListByCuentaSorted.isEmpty()){
            Cuenta cuenta = cuentaOpt.get();
            BigDecimal saldoInicial = cuenta.getSaldoInicial();

            movimiento = doMovimiento(movimiento, saldoInicial);

            return movimientoRepository.save(movimiento);
        }

        BigDecimal saldoActual = movimientosListByCuentaSorted.get(0).getSaldo();
        movimiento = doMovimiento(movimiento, saldoActual);

        return movimientoRepository.save(movimiento);
    }

    @Override
    @SneakyThrows
    public MovimientoDTO updateMovimiento(MovimientoDTO movimientoDTO) {

        String movimientoId = movimientoDTO.getMovimientoId();
        if(!movimientoRepository.existsById(movimientoId)) throw new MovimientoNotFoundException("Movimiento con id " + movimientoId + " no existe");

        ObjectMapper mapper = new ObjectMapper();
        Movimiento cliente = mapper.convertValue(movimientoDTO, Movimiento.class);

        movimientoRepository.save(cliente);

        return movimientoDTO;
    }

    @Override
    @SneakyThrows
    public void deleteMovimiento(String movimientoId) {

        if(!movimientoRepository.existsById(movimientoId)) throw new MovimientoNotFoundException("Movimiento con id " + movimientoId + " no existe");

        movimientoRepository.deleteById(movimientoId);
    }

    private static Movimiento doMovimiento(Movimiento movimiento, BigDecimal saldo) throws InvalidMovimientoTypeException, SaldoNoDisponibleException {
        String tipoMovimiento = movimiento.getTipoMovimiento().toLowerCase();
        BigDecimal valor = movimiento.getValor();

        switch (tipoMovimiento){
            case "retiro":
                if(valor.compareTo(saldo) == 1){
                    throw new SaldoNoDisponibleException("Saldo no disponible");
                }
                saldo = saldo.subtract(valor);
                valor = valor.multiply(new BigDecimal(-1));
                break;
            case "deposito":
                saldo = saldo.add(valor);
                break;
            default:
                throw new InvalidMovimientoTypeException("Tipo de movimiento invalido");
        }
        movimiento.setSaldo(saldo);
        movimiento.setValor(valor);

        return movimiento;
    }

    private static Date formatStringToDate(String fecha) throws ParseException {
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy"); // Input format
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00"); // Desired output format

        Date parsedDate = inputFormat.parse(fecha);
        String output = outputFormat.format(parsedDate) ;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.parse(output);
    }
}
