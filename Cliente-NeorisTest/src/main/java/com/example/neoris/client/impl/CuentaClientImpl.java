package com.example.neoris.client.impl;

import com.example.neoris.client.CuentaClient;
import com.example.neoris.dto.ClienteDTO;
import com.example.neoris.dto.CuentaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CuentaClientImpl implements CuentaClient {

    @Value("${config.client.cuentas.url}")
    private String cuentasUrl;

    private final RestTemplate restTemplate;

    public CuentaClientImpl() {
        this.restTemplate = new RestTemplateBuilder()
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build();
    }

    @Override
    public List<CuentaDTO> getCuentasByCliente(Integer clienteId) {

        String cuentaUrl = cuentasUrl + "/client/" + clienteId;

        ParameterizedTypeReference<List<CuentaDTO>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<CuentaDTO>> cuenta = restTemplate.exchange(cuentaUrl, HttpMethod.GET, null, responseType);

        return cuenta.getBody();
    }
}
