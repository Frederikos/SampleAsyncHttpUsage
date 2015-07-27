package com.test.asynchttpsampletemplate.network;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.loopj.android.http.AsyncHttpClient;
import com.test.asynchttpsampletemplate.R;
import com.test.asynchttpsampletemplate.network.listeners.JsonResultListener;
import com.test.asynchttpsampletemplate.network.listeners.PlacesCallback;
import com.test.asynchttpsampletemplate.utils.Utils;

import org.json.JSONObject;

import timber.log.Timber;

public class RestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    private static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    //execute this method for every request. put additional pre request logic here.
    private static boolean checkAndSetupRestClientForRequest(Activity activity, JsonResultListener callback) {
        Timber.tag(RestClient.class.getSimpleName());
        boolean result = isOnline(activity);

        if (!result) {
            Utils.showErrorInSnackBar(activity, activity.getString(R.string.no_internet_connection));
            callback.onResult(null);
        }

        return result;
    }

    public static void getPlacesAsync(final Activity activity, final PlacesCallback placesCallback) {
        if (!checkAndSetupRestClientForRequest(activity, placesCallback)) return;

        client.get(RequestUrls.GET_PLACES, null, new RestClientResponseHandler(activity) {
            @Override
            public void onNeedRetry() {
                getPlacesAsync(activity, placesCallback);
            }

            @Override
            void onResult(JSONObject response) {
                placesCallback.onResult(response);
            }
        });
    }

}



