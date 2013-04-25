package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeActivity extends Activity {
    public static final String LATEST_LATITUDE = "LATEST_LATITUDE";
    public static final String LATEST_LONGITUDE = "LATEST_LONGITUDE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Context context = getApplicationContext();
        Intent service = new Intent(context, HardwareTriggerBackgroundService.class);
        context.startService(service);

        initLocationInfoListener();

    }

    private void initLocationInfoListener() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                updateLatLong(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
    }

    private void updateLatLong(Location location) {
        SharedPreferences sharedPreferences = getSharedPreferences(HardwareTriggerActivity.PREFERENCES_NAME, 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(LATEST_LATITUDE, String.valueOf(location.getLatitude()));
        editor.putString(LATEST_LONGITUDE, String.valueOf(location.getLongitude()));

        editor.commit();
    }

    public void showTwitterSpike(View view) {
        startActivity(new Intent(this, TwitterSpikeActivity.class));
    }

    public void showLocationSpike(View view) {
        startActivity(new Intent(this, LocationSpikeActivity.class));
    }

    public void showHardwareTriggerSpike(View view) {
        startActivity(new Intent(this, HardwareTriggerActivity.class));
    }

    public void showMultiButtonSpike(View view) {
        startActivity(new Intent(this, MultiButtonActivity.class));
    }

    public void showWizardSpike(View view) {
        startActivity(new Intent(this, ScreenSlideActivity.class));
    }

    private void displayToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(), toastMessage, LENGTH_SHORT);
        toast.show();
    }
}
