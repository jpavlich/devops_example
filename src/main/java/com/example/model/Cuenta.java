package com.example.model;

import java.math.BigDecimal;

public class Cuenta {
    private Long id;

    private String name;

    private BigDecimal saldo;

    public Cuenta() {
    }

    public Cuenta(String name, BigDecimal saldo) {
        this.name = name;
        this.saldo = saldo;
    }

    public Cuenta(Long id, String name, BigDecimal saldo) {
        this.id = id;
        this.name = name;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
