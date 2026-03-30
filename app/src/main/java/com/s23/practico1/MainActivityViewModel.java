package com.s23.practico1;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Locale;

public class MainActivityViewModel extends AndroidViewModel {

    private final ConversorModelo modelo;
    private final MutableLiveData<String> resultadoDolares = new MutableLiveData<>("");
    private final MutableLiveData<String> resultadoEuros = new MutableLiveData<>("");
    private final MutableLiveData<Boolean> dolaresHabilitado = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> eurosHabilitado = new MutableLiveData<>(false);
    private final MutableLiveData<Double> tipoCambioActual = new MutableLiveData<>(0.92);
    private final MutableLiveData<String> mensajeToast = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        modelo = new ConversorModelo(tipoCambioActual.getValue());
    }

    public LiveData<String> getResultadoDolares() { return resultadoDolares; }
    public LiveData<String> getResultadoEuros() { return resultadoEuros; }
    public LiveData<Boolean> getDolaresHabilitado() { return dolaresHabilitado; }
    public LiveData<Boolean> getEurosHabilitado() { return eurosHabilitado; }
    public LiveData<Double> getTipoCambioActual() { return tipoCambioActual; }
    public LiveData<String> getMensajeToast() { return mensajeToast; }

    public void actualizarTipoCambio(String nuevoValor) {
        try {
            if (nuevoValor == null || nuevoValor.isEmpty()) {
                mensajeToast.setValue("Ingrese un valor de cambio");
                return;
            }
            double valor = Double.parseDouble(nuevoValor);
            tipoCambioActual.setValue(valor);
            modelo.setTipoDeCambio(valor);
            mensajeToast.setValue("Tipo de cambio actualizado correctamente");
        } catch (NumberFormatException e) {
            mensajeToast.setValue("Valor de cambio inválido");
        }
    }

    public void configurarCampos(int idSeleccionado) {
        if (idSeleccionado == R.id.rbEuros) {
            dolaresHabilitado.setValue(true);
            eurosHabilitado.setValue(false);
        } else if (idSeleccionado == R.id.rbDolares) {
            dolaresHabilitado.setValue(false);
            eurosHabilitado.setValue(true);
        }
        resultadoDolares.setValue("");
        resultadoEuros.setValue("");
    }

    public void calcular(String inDolares, String inEuros) {
        try {
            if (Boolean.TRUE.equals(dolaresHabilitado.getValue())) {
                if (inDolares.isEmpty()) {
                    mensajeToast.setValue("Por favor, ingrese un monto en USD");
                    return;
                }
                double res = modelo.convertirAEuros(Double.parseDouble(inDolares));
                resultadoEuros.setValue(String.format(Locale.getDefault(), "%.2f EUR", res));
            } else if (Boolean.TRUE.equals(eurosHabilitado.getValue())) {
                if (inEuros.isEmpty()) {
                    mensajeToast.setValue("Por favor, ingrese un monto en EUR");
                    return;
                }
                double res = modelo.convertirADolares(Double.parseDouble(inEuros));
                resultadoDolares.setValue(String.format(Locale.getDefault(), "%.2f USD", res));
            } else {
                mensajeToast.setValue("Seleccione una opción de conversión");
            }
        } catch (NumberFormatException e) {
            mensajeToast.setValue("Error en los datos ingresados");
        }
    }
}
