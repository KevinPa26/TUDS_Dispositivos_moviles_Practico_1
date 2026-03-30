package com.s23.practico1;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Locale;

public class MainActivityViewModel extends AndroidViewModel {

    private final ConversorModelo modelo;
    private final MutableLiveData<String> resultadoDolares = new MutableLiveData<>("");
    private final MutableLiveData<String> resultadoEuros = new MutableLiveData<>("");
    private final MutableLiveData<Integer> rbSeleccionadoId = new MutableLiveData<>(R.id.rbEuros);
    private final MutableLiveData<Double> tipoCambioActual = new MutableLiveData<>(0.92);
    private final MutableLiveData<String> mensajeToast = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        modelo = new ConversorModelo(tipoCambioActual.getValue());
    }

    public LiveData<String> getResultadoDolares() { return resultadoDolares; }
    public LiveData<String> getResultadoEuros() { return resultadoEuros; }
    public LiveData<Integer> getRbSeleccionadoId() { return rbSeleccionadoId; }
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
            mensajeToast.setValue("Tipo de cambio actualizado");
        } catch (NumberFormatException e) {
            mensajeToast.setValue("Valor de cambio inválido");
        }
    }

    public void configurarCampos(int idSeleccionado) {
        rbSeleccionadoId.setValue(idSeleccionado);
        // Al limpiar aquí, la Vista reaccionará borrando el texto
        resultadoDolares.setValue("");
        resultadoEuros.setValue("");
    }

    public void calcular(String inDolares, String inEuros) {
        Integer id = rbSeleccionadoId.getValue();
        if (id == null) return;

        try {
            if (id == R.id.rbEuros) {
                if (inDolares.isEmpty()) {
                    mensajeToast.setValue("Ingrese monto en USD");
                    return;
                }
                double res = modelo.convertirAEuros(Double.parseDouble(inDolares));
                resultadoEuros.setValue(String.format(Locale.getDefault(), "%.2f EUR", res));
            } else if (id == R.id.rbDolares) {
                if (inEuros.isEmpty()) {
                    mensajeToast.setValue("Ingrese monto en EUR");
                    return;
                }
                double res = modelo.convertirADolares(Double.parseDouble(inEuros));
                resultadoDolares.setValue(String.format(Locale.getDefault(), "%.2f USD", res));
            }
        } catch (NumberFormatException e) {
            mensajeToast.setValue("Error en los datos");
        }
    }
}
