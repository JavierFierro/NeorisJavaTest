package com.example.neoris.cliente;

import com.example.neoris.entity.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void test_clienteEntity(){
        String contrasena = "1234";

        Cliente cliente = new Cliente(123, "1234", true);

        assertNotNull(cliente, "Cliente must not be null");
        assertEquals(contrasena, cliente.getContrasena(), "Cliente contrasena must match");

    }
}
