package com.mx.Banco.service;

import com.mx.Banco.dao.BancosDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BancosServiceTest {

    @Mock
    private BancosDao bancosDao;

    @InjectMocks
    private BancosServiceImpl bancosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTotalBancos() {
        // Configurar el comportamiento del mock
        when(bancosDao.totalBancos()).thenReturn(5);

        // Llamar al método del servicio
        int totalBancos = bancosService.totalBancos();

        // Verificar el resultado
        assertEquals(5, totalBancos);
    }
}
