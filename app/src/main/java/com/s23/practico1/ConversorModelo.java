package com.s23.practico1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConversorModelo {
    private double tipoDeCambio; // 1 USD = X EUR

    public ConversorModelo(double tipoInicial) {
        this.tipoDeCambio = tipoInicial;
    }

    public double getTipoDeCambio() {
        return tipoDeCambio;
    }

    public void setTipoDeCambio(double tipoDeCambio) {
        this.tipoDeCambio = tipoDeCambio;
    }

    public double convertirAEuros(double dolares) {
        BigDecimal resultado = BigDecimal.valueOf(dolares * tipoDeCambio);
        resultado = resultado.setScale(2, RoundingMode.HALF_DOWN);
        return resultado.doubleValue();
    }

    public double convertirADolares(double euros) {
        BigDecimal resultado = BigDecimal.valueOf(euros / tipoDeCambio);
        resultado = resultado.setScale(2, RoundingMode.HALF_DOWN);
        return resultado.doubleValue();
    }
}
