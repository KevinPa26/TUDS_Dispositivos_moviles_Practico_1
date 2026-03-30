package com.s23.practico1;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private ConversorModelo modelo;
    private MutableLiveData<String> resultado = new MutableLiveData<>();
    // Dejo estas variables para que el equipo las use en la UI
    private MutableLiveData<Boolean> dolaresHabilitado = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> eurosHabilitado = new MutableLiveData<>(false);

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        // Inicializamos con un tipo de cambio base (1 USD = 0.92 EUR)
        modelo = new ConversorModelo(0.92);
    }

    public LiveData<String> getResultado() {
        return resultado;
    }

    public LiveData<Boolean> getDolaresHabilitado() {
        return dolaresHabilitado;
    }

    public LiveData<Boolean> getEurosHabilitado() {
        return eurosHabilitado;
    }

    // Método para  los RadioButtons
    public void configurarCampos(int idSeleccionado) {
        if (idSeleccionado == R.id.rbEuros) {
            dolaresHabilitado.setValue(true);
            eurosHabilitado.setValue(false);
        } else if (idSeleccionado == R.id.rbDolares) {
            dolaresHabilitado.setValue(false);
            eurosHabilitado.setValue(true);
        }
    }

    public void calcular(String valor, boolean esDolar) {
        try {
            double num = Double.parseDouble(valor);
            double res;
            if (esDolar) {
                res = modelo.convertirAEuros(num);
                resultado.setValue(res + " EUR");
            } else {
                res = modelo.convertirADolares(num);
                resultado.setValue(res + " USD");
            }
        } catch (Exception e) {
            resultado.setValue("Error");
        }
    }

    public void calcularVersionDos(String inDolares, String inEuros) {
        try {
            double res;

            if(dolaresHabilitado.getValue() && !eurosHabilitado.getValue()) {
                res = modelo.convertirAEuros(Double.parseDouble(inDolares));
                resultado.setValue(res + " EUR");
            } else if (eurosHabilitado.getValue() && !dolaresHabilitado.getValue()) {
                res = modelo.convertirADolares(Double.parseDouble(inEuros));
                resultado.setValue(res + " USD");
            } else {
                resultado.setValue("NADA");
            }
        } catch (Exception e) {
            resultado.setValue("ERROR");
            Log.d("ERROR", e.toString());
        }
    }
}
