package com.example.neoris.integration;

import com.example.neoris.controller.ReporteController;
import com.example.neoris.dto.ReporteDTO;
import com.example.neoris.service.ReporteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private ReporteController reporteController;

    @Mock
    private ReporteService reporteService;

    @Test
    public void testGetData_Success() {
        ReporteDTO reporteDTOResponse = new ReporteDTO(
                "15/02/2023 10:34:20",
                "Juan Osorio",
                "495878",
                "Ahorro",
                new BigDecimal("0.00"),
                true,
                new BigDecimal("150.00"),
                new BigDecimal("150.00")
        );

        List<ReporteDTO> reporteDTOList = Arrays.asList(reporteDTOResponse);
        Mockito.when(reporteService.generarReportePorFecha(8, "20/10/2022", "02/06/2023")).thenReturn(reporteDTOList);

        ResponseEntity<List<ReporteDTO>> response = reporteController.getClienteById(8, "20/10/2022", "02/06/2023");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), reporteDTOList);

    }
}
