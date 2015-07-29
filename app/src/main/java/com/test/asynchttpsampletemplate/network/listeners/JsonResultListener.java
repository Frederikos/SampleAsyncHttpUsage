package com.test.asynchttpsampletemplate.network.listeners;

import android.view.View;

import org.json.JSONObject;

public abstract class JsonResultListener {

    private View progressIndicator;

    public JsonResultListener(View progressIndicator) {
        if (progressIndicator != null) {
            this.progressIndicator = progressIndicator;
            progressIndicator.setVisibility(View.VISIBLE);
        }
    }

    public void onResult(JSONObject jsonObject) {
        if (progressIndicator != null) {
            progressIndicator.setVisibility(View.GONE);
        }
        onRequestComplete(jsonObject);
    }

    public abstract void onRequestComplete(JSONObject jsonObject);
}
