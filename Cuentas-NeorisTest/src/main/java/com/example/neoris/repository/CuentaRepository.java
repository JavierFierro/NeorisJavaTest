package com.example.neoris.repository;

import com.example.neoris.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    public List<Cuenta> getAllByClienteId(Integer clienteId);
}
