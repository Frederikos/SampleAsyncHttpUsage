package com.test.asynchttpsampletemplate.network.listeners;

import org.json.JSONObject;

public interface JsonResultCallback {
    void onResult(JSONObject jsonObject);
}
