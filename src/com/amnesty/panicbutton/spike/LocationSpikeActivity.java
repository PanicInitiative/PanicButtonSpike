package com.amnesty.panicbutton.spike;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class LocationSpikeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_spike);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> allProviders = locationManager.getAllProviders();

        StringBuilder result = new StringBuilder();
        for (String provider : allProviders) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(provider);

            double latitude = 0;
            double longitude = 0;
            if (lastKnownLocation != null) {
                latitude = lastKnownLocation.getLatitude();
                longitude = lastKnownLocation.getLongitude();
            }


            result.append("Provider : ").append(provider)
                    .append(", Lat : ").append(latitude)
                    .append(", Long : ").append(longitude).append("\n");
        }

        TextView locationView = (TextView) findViewById(R.id.locationInfo);
        locationView.setText(result.toString());

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                updateNetworkLocation(location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
    }

    public void updateNetworkLocation(Location location) {
        int id = getResources().getIdentifier(location.getProvider(), "id", getApplicationContext().getPackageName());
        TextView networkLocation = (TextView) findViewById(id);
        StringBuilder result = new StringBuilder();
        result.append("Provider : ").append(location.getProvider())
                .append(", Lat : ").append(location.getLatitude())
                .append(", Long : ").append(location.getLongitude())
                .append(", Time : ").append(location.getTime());

        networkLocation.setText(result.toString());
    }
}
