package com.example.service;

import java.math.BigDecimal;

import com.example.exceptions.CuentaException;
import com.example.model.Cuenta;
import com.example.repository.CuentaRepository;

public class CuentaService {

    private CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public BigDecimal saldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findCuentaById(cuentaId);
        return cuenta.getSaldo();
    }

    public void consignar(Long cuentaId, BigDecimal monto) {
        cuentaRepository.cambiarSaldo(cuentaId, monto);
    }

    public void retirar(Long cuentaId, BigDecimal monto) {
        if (monto.compareTo(saldo(cuentaId)) > 0) {
            throw new CuentaException();
        }
        cuentaRepository.cambiarSaldo(cuentaId, monto.negate());
    }

}
