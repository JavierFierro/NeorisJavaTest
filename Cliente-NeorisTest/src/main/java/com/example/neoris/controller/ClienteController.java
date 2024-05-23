package com.example.neoris.controller;

import com.example.neoris.client.CuentaClient;
import com.example.neoris.dto.ClienteDTO;
import com.example.neoris.entity.Cliente;
import com.example.neoris.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes(){

        List<Cliente> clienteList = clienteService.getAllCLientes();
        return new ResponseEntity<>(clienteList, HttpStatus.OK);
    }

    @GetMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable("id") Integer clienteId) {

        ClienteDTO clienteDTO = clienteService.getClienteById(clienteId);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> saveCliente(@RequestBody ClienteDTO clienteDTO){
        return new ResponseEntity<>(clienteService.saveCliente(clienteDTO), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO){
        return new ResponseEntity<>(clienteService.updateCliente(clienteDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="{id}")
    public void deleteCliente(@PathVariable("id") Integer clienteId){
        clienteService.deleteCliente(clienteId);
    }
}
