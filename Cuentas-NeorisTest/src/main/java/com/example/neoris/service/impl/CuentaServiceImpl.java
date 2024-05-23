package com.example.neoris.service.impl;

import com.example.neoris.dto.CuentaDTO;
import com.example.neoris.entity.Cuenta;
import com.example.neoris.exception.cuenta.CuentaNotFoundException;
import com.example.neoris.exception.movimiento.MovimientoNotFoundException;
import com.example.neoris.repository.CuentaRepository;
import com.example.neoris.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    @SneakyThrows
    public CuentaDTO getCuentaById(String cuentaId) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(
                () -> new CuentaNotFoundException("Cuenta con id " + cuentaId + " no encontrado")
        );

        ObjectMapper mapper = new ObjectMapper();
        CuentaDTO cuentaDTO = mapper.convertValue(cuenta, CuentaDTO.class);

        return cuentaDTO;
    }

    @Override
    public List<Cuenta> getCuentasByClientId(Integer clientId) {
        List<Cuenta> cuentasByClientId = cuentaRepository.getAllByClienteId(clientId);
        return cuentasByClientId;
    }

    @Override
    public Cuenta saveCuenta(CuentaDTO cuentaDTO) {

        ObjectMapper mapper = new ObjectMapper();
        Cuenta cuenta = mapper.convertValue(cuentaDTO, Cuenta.class);

        return cuentaRepository.save(cuenta);
    }

    @Override
    @SneakyThrows
    public CuentaDTO updateCuenta(CuentaDTO cuentaDTO) {

        String numeroCuenta = cuentaDTO.getNumeroCuenta();
        if(!cuentaRepository.existsById(numeroCuenta)) throw new MovimientoNotFoundException("Numero de cuenta " + numeroCuenta + " no existe");

        ObjectMapper mapper = new ObjectMapper();
        Cuenta cuenta = mapper.convertValue(cuentaDTO, Cuenta.class);

        cuentaRepository.save(cuenta);

        return cuentaDTO;
    }

    @Override
    @SneakyThrows
    public void deleteCuenta(String cuentaId) {
        if(!cuentaRepository.existsById(cuentaId)) throw new MovimientoNotFoundException("Numero de cuenta " + cuentaId + " no existe");
        cuentaRepository.deleteById(cuentaId);
    }
}
