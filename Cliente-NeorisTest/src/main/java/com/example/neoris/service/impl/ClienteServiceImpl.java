package com.example.neoris.service.impl;

import com.example.neoris.dto.ClienteDTO;
import com.example.neoris.entity.Cliente;
import com.example.neoris.exception.cliente.ClienteNotFoundException;
import com.example.neoris.repository.ClienteRepository;
import com.example.neoris.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllCLientes() {
        return clienteRepository.findAll();
    }

    @Override
    @SneakyThrows
    public ClienteDTO getClienteById(Integer clienteId) {

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new ClienteNotFoundException("Cliente con id " + clienteId + " no existe"));

        ObjectMapper mapper = new ObjectMapper();
        ClienteDTO clienteDTO = mapper.convertValue(cliente, ClienteDTO.class);

        return clienteDTO;
    }

    @Override
    public Cliente saveCliente(ClienteDTO clienteDTO) {

        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.convertValue(clienteDTO, Cliente.class);

        return clienteRepository.save(cliente);
    }

    @Override
    @SneakyThrows
    public ClienteDTO updateCliente(ClienteDTO clienteDTO) {

        if(!clienteRepository.existsById(clienteDTO.getClienteId())) throw new ClienteNotFoundException("Cliente con id " + clienteDTO.getClienteId() + " no existe");

        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.convertValue(clienteDTO, Cliente.class);

        clienteRepository.save(cliente);

        return clienteDTO ;
    }

    @Override
    @SneakyThrows
    public void deleteCliente(Integer clienteId) {

        if(!clienteRepository.existsById(clienteId)) throw new ClienteNotFoundException("Cliente con id " + clienteId + " no existe");

        clienteRepository.deleteById(clienteId);
    }
}
