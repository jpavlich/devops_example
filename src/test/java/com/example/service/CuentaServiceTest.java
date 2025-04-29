package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.exceptions.CuentaException;
import com.example.init.DBInitializer;
import com.example.persistencia.DBConnectionManager;
import com.example.repository.CuentaRepository;

public class CuentaServiceTest {

    CuentaService cuentaService;

    @BeforeEach
    void init() {
        DBConnectionManager connMgr = new DBConnectionManager();
        CuentaRepository cuentaRepository = new CuentaRepository(connMgr);
        cuentaService = new CuentaService(cuentaRepository);

        new DBInitializer(connMgr).initDB();
    }

    @Test
    void retiraCorrectamente() {
        cuentaService.retirar(1l, new BigDecimal("500.00"));
        BigDecimal actualSaldo = cuentaService.saldo(1l);
        assertEquals(new BigDecimal("45050.00"), actualSaldo);
    }

    @Test
    void retiraMasDelSaldo() {
        try {
            cuentaService.retirar(1l, new BigDecimal("5500.00"));
            fail();
        } catch (CuentaException e) {

        }

    }

    @Test
    void depositaCorrectamente() {
        cuentaService.consignar(1l, new BigDecimal("500.00"));
        BigDecimal actualSaldo = cuentaService.saldo(1l);
        assertEquals(new BigDecimal("5500.00"), actualSaldo);
    }

    @Test
    void saldoCorrecto() {
        BigDecimal actualSaldo = cuentaService.saldo(2l);

        assertEquals(new BigDecimal("4000.00"), actualSaldo);
    }
}
