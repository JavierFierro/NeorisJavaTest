package com.example.neoris.exception.handler;

import com.example.neoris.exception.cuenta.CuentaNotFoundException;
import com.example.neoris.exception.movimiento.InvalidMovimientoTypeException;
import com.example.neoris.exception.movimiento.MovimientoNotFoundException;
import com.example.neoris.exception.movimiento.SaldoNoDisponibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(MovimientoNotFoundException.class)
    public ResponseEntity<String> handleMovimientoNotFoundException(MovimientoNotFoundException ex) {
        logger.info("MovimientoNotFoundException caught");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(CuentaNotFoundException.class)
    public ResponseEntity<String> handleCuentaNotFoundException(CuentaNotFoundException ex) {
        logger.info("CuentaNotFoundException caught");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(InvalidMovimientoTypeException.class)
    public ResponseEntity<String> handleInvalidMovimientoTypeException(InvalidMovimientoTypeException ex) {
        logger.info("InvalidMovimientoTypeException caught");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<String> handleSaldoNoDisponibleException(SaldoNoDisponibleException ex) {
        logger.info("SaldoNoDisponibleException caught");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
