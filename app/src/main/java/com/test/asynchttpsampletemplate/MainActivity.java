package com.test.asynchttpsampletemplate;

import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.test.asynchttpsampletemplate.databinding.ActivityMainBinding;
import com.test.asynchttpsampletemplate.network.RestClient;
import com.test.asynchttpsampletemplate.network.listeners.Callback;
import com.test.asynchttpsampletemplate.network.models.PlaceModel;
import com.test.asynchttpsampletemplate.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MainActivityViewModel());
    }

    public class MainActivityViewModel extends BaseObservable {
        public final ObservableField<Boolean> isDataLoading = new ObservableField<>();
        public final ObservableField<String> placesText = new ObservableField<>();

        public MainActivityViewModel() {
            isDataLoading.set(false);
        }

        public View.OnClickListener onBtnLoadClick() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RestClient.getPlacesAsync(MainActivity.this, new Callback<List<PlaceModel>>(isDataLoading) {
                        @Override
                        public void onRequestComplete(List<PlaceModel> places) {
                            placesText.set("Count loaded places - " + places.size());
                        }
                    });
                }
            };
        }
    }
}
