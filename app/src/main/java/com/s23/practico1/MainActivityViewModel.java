package com.s23.practico1;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Locale;

public class MainActivityViewModel extends AndroidViewModel {

    private final MutableLiveData<ConversorModelo> conversorModelo = new MutableLiveData<>(new ConversorModelo());
    private final SingleLiveEvent<String> mensajeToast = new SingleLiveEvent<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<ConversorModelo> getConversorModelo() { return conversorModelo; }
    public LiveData<String> getMensajeToast() { return mensajeToast; }
    public void actualizarTipoCambio(String nuevoValor) {
        try {
            ConversorModelo cm = conversorModelo.getValue();
            if(cm != null) {
                if (nuevoValor == null || nuevoValor.isEmpty()) {
                    mensajeToast.setValue("Ingrese un valor de cambio");
                    return;
                }

                double valor = Double.parseDouble(nuevoValor);
                cm.setTipoDeCambio(valor);
                conversorModelo.setValue(cm);
                mensajeToast.setValue("Tipo de cambio actualizado");
            }
        } catch (NumberFormatException e) {
            mensajeToast.setValue("Valor de cambio inválido");
        }
    }
    public void dolaresChecked() {
        ConversorModelo cm = conversorModelo.getValue();
        if(cm != null) {
            cm.checkedRBDolares();
            conversorModelo.setValue(cm);
        }
    }
    public void eurosChecked() {
        ConversorModelo cm = conversorModelo.getValue();
        if(cm != null) {
            cm.checkedRBEuros();
            conversorModelo.setValue(cm);
        }
    }
    public void calcular(String inDolares, String inEuros) {
        ConversorModelo cm = conversorModelo.getValue();
        if (cm == null) return;

        try {
            if (cm.isRbEuros()) {
                if (inDolares.isEmpty()) {
                    mensajeToast.setValue("Ingrese monto en USD");
                    return;
                }
                cm.convertirAEuros(inDolares);
                conversorModelo.setValue(cm);
            } else if (cm.isRbDolares()) {
                if (inEuros.isEmpty()) {
                    mensajeToast.setValue("Ingrese monto en EUR");
                    return;
                }
                cm.convertirADolares(inEuros);
                conversorModelo.setValue(cm);
            }
        } catch (Exception e) {
            Log.d("ERROR - VIEWMODEL", e.toString());
            mensajeToast.setValue("Error en los datos");
        }
    }
}
