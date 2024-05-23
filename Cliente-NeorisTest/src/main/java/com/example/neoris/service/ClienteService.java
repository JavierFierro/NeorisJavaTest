package com.example.neoris.service;

import com.example.neoris.dto.ClienteDTO;
import com.example.neoris.entity.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteService {
    public List<Cliente> getAllCLientes();
    public ClienteDTO getClienteById(Integer clienteId);
    public Cliente saveCliente(ClienteDTO cliente);
    public ClienteDTO updateCliente(ClienteDTO cliente);
    public void deleteCliente(Integer clienteId);
}
