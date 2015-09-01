package com.test.asynchttpsampletemplate.network;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.test.asynchttpsampletemplate.R;
import com.test.asynchttpsampletemplate.utils.Utils;

import org.apache.http.Header;
import org.json.JSONObject;

import timber.log.Timber;

public abstract class RestClientResponseHandler extends JsonHttpResponseHandler {

    private Activity activity;

    public RestClientResponseHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Timber.d("onSuccess, RequestURL: " + getRequestURI());
        printRequestResult(statusCode, headers);
        Timber.d(response.toString());
        onResult(response);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable t, JSONObject response) {
        Timber.d("onFailure, RequestURL: " + getRequestURI());
        printRequestResult(statusCode, headers);
        if (response != null) Timber.d(response.toString());
        //add retry logic if needed
        if (response == null && statusCode == 0) {
            onNeedRetry();
        } else {
            Utils.showErrorInSnackBar(activity, (response != null) ? response.optString("error", activity.getString(R.string.network_error)) : activity.getString(R.string.network_error));
            onResult(null);
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String s, Throwable t) {
        Timber.d("onFailure, RequestURL: " + getRequestURI());
        printRequestResult(statusCode, headers);
        if (s != null) Timber.d(s);
        Utils.showErrorInSnackBar(activity, activity.getString(R.string.network_error));
        onResult(null);
    }

    private void printRequestResult(int statusCode, Header[] headers) {
        String s = "Response status: " + Integer.toString(statusCode);
        if (headers != null) {
            String result = s + " headers #" + Integer.toString(headers.length);
            for (int i = 0; i < headers.length; i++) {
                result += " Header " + headers[i].getName() + " : " + headers[i].getValue();
            }
            Timber.d(result);
        } else
            Timber.d("headers = null");
    }

    abstract void onNeedRetry();

    abstract void onResult(JSONObject response);

}
