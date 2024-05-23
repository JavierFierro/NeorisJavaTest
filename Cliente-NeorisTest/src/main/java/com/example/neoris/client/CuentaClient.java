package com.example.neoris.client;

import com.example.neoris.dto.ClienteDTO;
import com.example.neoris.dto.CuentaDTO;

import java.util.List;

public interface CuentaClient {
    public List<CuentaDTO> getCuentasByCliente(Integer clienteId);
}
