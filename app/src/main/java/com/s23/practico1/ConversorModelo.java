package com.s23.practico1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class ConversorModelo {
    private double tipoDeCambio = 0.92; // 1 USD = 0.92 EUR
    private boolean inputDolares = false, inputEuros = false, rbDolares = false, rbEuros = false;
    private String resultadoDolares = "", resultadoEuros = "";

    public ConversorModelo(double tipoDeCambio, boolean inputDolares, boolean inputEuros, boolean rbDolares, boolean rbEuros, String resultadoDolares, String resultadoEuros) {
        this.tipoDeCambio = tipoDeCambio;
        this.inputDolares = inputDolares;
        this.inputEuros = inputEuros;
        this.rbDolares = rbDolares;
        this.rbEuros = rbEuros;
        this.resultadoDolares = resultadoDolares;
        this.resultadoEuros = resultadoEuros;
    }

    public ConversorModelo() {}

    public double getTipoDeCambio() {
        return tipoDeCambio;
    }

    public boolean isInputDolares() {
        return inputDolares;
    }

    public boolean isInputEuros() {
        return inputEuros;
    }

    public boolean isRbDolares() {
        return rbDolares;
    }

    public boolean isRbEuros() {
        return rbEuros;
    }

    public String getResultadoDolares() {
        return resultadoDolares;
    }

    public String getResultadoEuros() {
        return resultadoEuros;
    }

    public void setTipoDeCambio(double tipoDeCambio) {
        this.resultadoDolares = "";
        this.resultadoEuros = "";
        this.tipoDeCambio = tipoDeCambio;
    }

    public void checkedRBDolares(){
        rbDolares = true;
        rbEuros = false;
        inputDolares = false;
        inputEuros = true;
        resultadoDolares = "";
        resultadoEuros = "";
    }

    public void checkedRBEuros() {
        rbDolares = false;
        rbEuros = true;
        inputDolares = true;
        inputEuros = false;
        resultadoDolares = "";
        resultadoEuros = "";
    }

    public void convertirAEuros(String dolares) {
        resultadoDolares = dolares;
        double doubleDolares = Double.parseDouble(dolares);
        BigDecimal resultado = BigDecimal.valueOf(doubleDolares * tipoDeCambio);
        resultado = resultado.setScale(2, RoundingMode.HALF_DOWN);
        resultadoEuros = String.format(Locale.getDefault(), "%.2f EUR", resultado.doubleValue());
    }

    public void convertirADolares(String euros) {
        resultadoEuros = euros;
        double doubleEuros = Double.parseDouble(euros);
        BigDecimal resultado = BigDecimal.valueOf(doubleEuros / tipoDeCambio);
        resultado = resultado.setScale(2, RoundingMode.HALF_DOWN);
        resultadoDolares = String.format(Locale.getDefault(), "%.2f USD", resultado.doubleValue());
    }
}
