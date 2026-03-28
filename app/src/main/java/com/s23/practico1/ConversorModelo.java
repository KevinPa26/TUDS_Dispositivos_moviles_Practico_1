package com.s23.practico1;

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
        return dolares * tipoDeCambio;
    }

    public double convertirADolares(double euros) {
        return euros / tipoDeCambio;
    }
}
