package com.example.neoris.controller;

import com.example.neoris.dto.CuentaDTO;
import com.example.neoris.dto.MovimientoDTO;
import com.example.neoris.entity.Cuenta;
import com.example.neoris.entity.Movimiento;
import com.example.neoris.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping()
    public ResponseEntity<List<Cuenta>> getAllCuentas(){

        List<Cuenta> cuentaList = cuentaService.getAllCuentas();
        return new ResponseEntity<>(cuentaList, HttpStatus.OK);
    }

    @GetMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaDTO> getCuentaById(@PathVariable("id") String cuentaId) {

        CuentaDTO cuentaoDTO = cuentaService.getCuentaById(cuentaId);
        return new ResponseEntity<>(cuentaoDTO, HttpStatus.OK);

    }

    @GetMapping(value="client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cuenta>> getCuentaByClientId(@PathVariable("id") Integer clientId) {

        List<Cuenta> cuentaList = cuentaService.getCuentasByClientId(clientId);
        return new ResponseEntity<>(cuentaList, HttpStatus.OK);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> saveCuenta(@RequestBody CuentaDTO cuentaDTO){
        return new ResponseEntity<>(cuentaService.saveCuenta(cuentaDTO), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaDTO> updateCuenta(@RequestBody CuentaDTO cuentaDTO){
        return new ResponseEntity<>(cuentaService.updateCuenta(cuentaDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="{id}")
    public void deleteCuenta(@PathVariable("id") String cuentaId){
        cuentaService.deleteCuenta(cuentaId);
    }

}
