package com.s23.practico1;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

//Clase de tipo evento para no permitir redisparar el mensaje en Toast al rotar pantalla.
public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private boolean pendiente = false;

    @Override
    public void observe(LifecycleOwner owner, Observer<? super T> observer) {
        super.observe(owner, t -> {
            if (pendiente) {
                pendiente = false;
                observer.onChanged(t);
            }
        });
    }

    @Override
    public void setValue(T t) {
        pendiente = true;
        super.setValue(t);
    }
}
