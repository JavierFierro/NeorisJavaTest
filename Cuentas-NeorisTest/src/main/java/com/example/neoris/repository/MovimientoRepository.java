package com.example.neoris.repository;

import com.example.neoris.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, String> {

    @Query(value = "SELECT * FROM movimiento WHERE numero_cuenta = ?1 AND TO_DATE(fecha, 'DD/MM/YYY') BETWEEN ?2 AND ?3", nativeQuery = true)
    List<Movimiento> findMovimientosBetweenDates(String numeroCuenta, Date startDate, Date endDate);

    List<Movimiento> findByNumeroCuentaOrderByFechaDesc(String numeroCuenta);
}
