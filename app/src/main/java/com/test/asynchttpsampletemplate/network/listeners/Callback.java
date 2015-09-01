package com.test.asynchttpsampletemplate.network.listeners;

import android.view.View;

public abstract class Callback<T> {

    private View progressIndicator;

    public Callback() {}

    public Callback(View progressIndicator) {
        this.progressIndicator = progressIndicator;
        if (progressIndicator != null) {
            progressIndicator.setVisibility(View.VISIBLE);
        }
    }

    public void onResult(T result) {
        if (progressIndicator != null) {
            progressIndicator.setVisibility(View.GONE);
        }
        onRequestComplete(result);
    }

    public abstract void onRequestComplete(T result);
}
