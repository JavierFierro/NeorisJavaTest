package com.example.neoris.client.impl;

import com.example.neoris.client.MovimientoClient;
import com.example.neoris.dto.CuentaDTO;
import com.example.neoris.dto.MovimientoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class MovimientoClientImpl implements MovimientoClient {

    @Value("${config.client.movimientos.url}")
    private String cuentasUrl;

    private final RestTemplate restTemplate;

    public MovimientoClientImpl() {
        this.restTemplate = new RestTemplateBuilder()
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build();
    }

    @Override
    public List<MovimientoDTO> getMovimientosByCuentaAndDateRange(String numeroCuenta, String fechaDesde, String fechaHasta) {
        String movimientoUrl = cuentasUrl + "/cuenta/" + numeroCuenta;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(movimientoUrl)
                .queryParam("fechaDesde", fechaDesde)
                .queryParam("fechaHasta", fechaHasta);

        String url = builder.buildAndExpand().toUriString();

        ParameterizedTypeReference<List<MovimientoDTO>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<MovimientoDTO>> cuenta = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        return cuenta.getBody();
    }
}
