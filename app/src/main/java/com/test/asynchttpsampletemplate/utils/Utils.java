package com.test.asynchttpsampletemplate.utils;

import android.app.Activity;

import com.github.mrengineer13.snackbar.SnackBar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.asynchttpsampletemplate.R;

public class Utils {

    private static Gson gson;

    public static void showErrorInSnackBar(Activity activity, String errorMessage) {
        new SnackBar.Builder(activity)
                .withMessage(errorMessage)
                .withTextColorId(R.color.white)
                .withBackgroundColorId(R.color.red)
                .show();
    }

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            gson = gsonBuilder.create();
        }
        return gson;
    }

}
