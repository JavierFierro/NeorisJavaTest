package com.example.neoris.controller;

import com.example.neoris.dto.MovimientoDTO;
import com.example.neoris.entity.Movimiento;
import com.example.neoris.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping()
    public ResponseEntity<List<Movimiento>> getAllMovimientos(){

        List<Movimiento> movimientoList = movimientoService.getAllMovimientos();
        return new ResponseEntity<>(movimientoList, HttpStatus.OK);
    }

    @GetMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovimientoDTO> getMovimientoById(@PathVariable("id") String movimientoId) {
        MovimientoDTO movimientoDTO = movimientoService.getMovimientoById(movimientoId);
        return new ResponseEntity<>(movimientoDTO, HttpStatus.OK);
    }

    @GetMapping(value="cuenta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movimiento>> getMovimientosByNumeroCuentaAndDateRange(
            @PathVariable("id") String numeroCuenta,
            @RequestParam String fechaDesde,
            @RequestParam String fechaHasta) {
        List<Movimiento> movimientosList = movimientoService.getMovimientosByNumeraCuentaAndDateRange(
                numeroCuenta, fechaDesde, fechaHasta);
        return new ResponseEntity<>(movimientosList, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movimiento> saveMovimiento(@RequestBody MovimientoDTO movimientoDTO){
        return new ResponseEntity<>(movimientoService.saveMovimiento(movimientoDTO), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovimientoDTO> updateMovimiento(@RequestBody MovimientoDTO movimientoDTO){
        return new ResponseEntity<>(movimientoService.updateMovimiento(movimientoDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="{id}")
    public void deleteMovimiento(@PathVariable("id") String movimentoId){
        movimientoService.deleteMovimiento(movimentoId);
    }

}
