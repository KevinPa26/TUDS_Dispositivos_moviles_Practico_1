package com.s23.practico1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> dolarMutable;
    private MutableLiveData<Integer> euroMutable;
    private MutableLiveData<Integer>  valorConvMutable;
    private MutableLiveData<String> radioBtnMutable;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Integer> getDolarMutable() {
        if(dolarMutable == null) {
            dolarMutable = new MutableLiveData<>();
            /// setter del dinnero ingresado
            dolarMutable.setValue(0);
        }
        return dolarMutable;
    }

    public LiveData<Integer> getEuroMutable() {
        if(euroMutable == null) {
            euroMutable = new MutableLiveData<>();
            euroMutable.setValue(0);
        }
        return euroMutable;
    }
}
