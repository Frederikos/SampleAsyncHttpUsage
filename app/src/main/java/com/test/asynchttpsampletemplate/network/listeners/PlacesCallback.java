package com.test.asynchttpsampletemplate.network.listeners;

import android.view.View;

import com.test.asynchttpsampletemplate.network.models.PlaceModel;
import com.test.asynchttpsampletemplate.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import timber.log.Timber;

public abstract class PlacesCallback extends JsonResultListener {

    public PlacesCallback(View progressView) {
        super(progressView);
    }

    @Override
    public void onRequestComplete(JSONObject jsonObject) {
        if (jsonObject != null) {
            ArrayList<PlaceModel> result = new ArrayList<>();
            try {
                JSONArray items = jsonObject.getJSONObject("block").getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    result.add(Utils.getGson().fromJson(items.getJSONObject(i).toString(), PlaceModel.class));
                }
            } catch (JSONException ex) {
                Timber.e(ex.toString());
            }
            requestComplete(result);
        } else {
            requestComplete(null);
        }
    }

    public abstract void requestComplete(ArrayList<PlaceModel> places);

}
