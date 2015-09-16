package com.test.asynchttpsampletemplate.network.listeners;

import android.databinding.ObservableField;

public abstract class Callback<T> {

    private ObservableField<Boolean> dataLoadingObserver;

    public Callback() {
    }

    public Callback(ObservableField<Boolean> dataLoadingObserver) {
        this.dataLoadingObserver = dataLoadingObserver;
        if (dataLoadingObserver != null) {
            dataLoadingObserver.set(true);
        }
    }

    public void onResult(T result) {
        if (dataLoadingObserver != null) {
            dataLoadingObserver.set(false);
        }
        onRequestComplete(result);
    }

    public abstract void onRequestComplete(T result);
}
