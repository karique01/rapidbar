package pe.edu.upc.rapidbar;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class RapidBarApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
