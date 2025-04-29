package com.example;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.example.persistencia.DBConnectionManager;
import com.example.repository.CuentaRepository;
import com.example.service.CuentaService;
public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnectionManager connMgr = new DBConnectionManager();
        CuentaRepository cuentaRepository = new CuentaRepository(connMgr);
        CuentaService cuentaService = new CuentaService(cuentaRepository);

        cuentaService.consignar(1l, new BigDecimal("1000.00"));
        cuentaService.retirar(1l, new BigDecimal("500.00"));

        System.out.println(cuentaService.saldo(1l));

    }
}