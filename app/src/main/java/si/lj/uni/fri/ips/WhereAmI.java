package si.lj.uni.fri.ips;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;

public class WhereAmI extends Activity implements LocationListener {

    private LocationManager myLocationManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.where_am_i);

        myLocationManger = (LocationManager) getSystemService(LOCATION_SERVICE);


        Toast.makeText(this, "JAP", Toast.LENGTH_SHORT);
        try {
            myLocationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,
                    1, this);
        } catch (SecurityException se){
            Toast.makeText(this, "Exception" + se, Toast.LENGTH_SHORT);
        }

        //OurWifiList.getAvailableWifiList();

        float r1 = 0;
        float r2 = 0;
        float r3 = 0;
        float rx1 = 0;
        float rx2 = 0;
        float rx3 = 0;
        float maxRange = 15;

        AccessPoint ap1;
        AccessPoint ap2;
        AccessPoint ap3;

        List<AccessPoint> accessPoints = AccessPoints.getAccessPoints();
        int index = 0;
        for(AccessPoint accessPoint : accessPoints){
            if(accessPoint.getRSSI() != 0){
                //Toast.makeText(this, accessPoint.getBSSID()+ " " + accessPoint.getRSSI(), Toast.LENGTH_SHORT).show();
                Log.d("accesspoint", "got accesspoint");
              if(index == 0){
                  ap1 = accessPoint;
                  r1= ((float) calculateDistance(accessPoint.getRSSI(), 2462));
                  rx1 =  (float) Math.abs(maxRange * Math.log10(1-(((float)(100.0/60.0) * (float)(Math.abs(accessPoint.getRSSI() + 40)))/100.0)));
                  Toast.makeText(this, r1 + "m " + rx1 + "m " + accessPoint.getBSSID()+ " " + accessPoint.getRSSI(), Toast.LENGTH_LONG).show();
                  ((TextView) findViewById(R.id.BSSID1)).setText("RSSI: " + ap1.getRSSI() + "dBm BSSID: " + ap1.getBSSID());
                  ((TextView) findViewById(R.id.Radius1)).setText("x: " + ap1.getX() + " y: " + ap1.getY() + " z: " + ap1.getZ() + " Distance: " + rx1 + "m");
              }else if(index == 1){
                  ap2 = accessPoint;
                  r2= ((float) calculateDistance(accessPoint.getRSSI(), 2437));
                  rx2 =  (float) Math.abs(maxRange * Math.log10(1-(((float)(100.0/60.0) * (float)(Math.abs(accessPoint.getRSSI() + 40)))/100.0)));  //previous version // (float) (maxRange *((((float)(100.0/60.0) * (float)(Math.abs(accessPoint.getRSSI() + 40)))/100.0)));
                  ((TextView) findViewById(R.id.BSSID2)).setText("RSSI: " + ap2.getRSSI() + "dBm BSSID: " + ap2.getBSSID());
                  ((TextView) findViewById(R.id.Radius2)).setText("x: " + ap2.getX() + " y: " + ap2.getY() + " z: " + ap2.getZ() + " Distance: " + rx2 + "m");
                  Toast.makeText(this, r2 + "m " + rx2 + "m " + accessPoint.getBSSID()+ " " + accessPoint.getRSSI(), Toast.LENGTH_LONG).show();
              }else if(index == 2){
                  ap3 = accessPoint;
                  r3= ((float) calculateDistance(accessPoint.getRSSI(), 2484));
                  rx3 = (float) Math.abs(maxRange * Math.log10(1-(((float)(100.0/60.0) * (float)(Math.abs(accessPoint.getRSSI() + 40)))/100.0)));
                  ((TextView) findViewById(R.id.BSSID3)).setText("RSSI: " + ap3.getRSSI() + "dBm BSSID: " + ap3.getBSSID());
                  ((TextView) findViewById(R.id.Radius3)).setText("x: " + ap3.getX() + " y: " + ap3.getY() + " z: " + ap3.getZ() + " Distance: " + rx3 + "m");
                  Toast.makeText(this, r3 + "m " + rx3 + "m " + accessPoint.getBSSID()+ " " + accessPoint.getRSSI(), Toast.LENGTH_LONG).show();
              }
                index++;
            }
        }
        float x = 0;
        float y = 0;
        float z = 0;

        x = ((float)(((rx2*rx2) - (rx3*rx3) + (6.0 * 6.0)) / (2.0 * 6.0)));
        y = (float) ((float) ((((rx2*rx2)-(rx1*rx1) + (5.0*5.0) + (4.0*4.0))/ (2.0 * 4.0)) - ((5.0/4.0) * x)));
        ((TextView) findViewById(R.id.Position)).setText("x: " + x + "m y: " + y + "m z: " + z + " floor ");
        Toast.makeText(this,"x:" + x + "m y:" + y + "m this is a try" , Toast.LENGTH_LONG).show();
        getLocation();
    }

    public double calculateDistance(double signalLevelInDb, double freqInMHz) {
        double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(signalLevelInDb)) / 20.0;
        return Math.pow(10.0, exp);
    }

    // flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    // flag for GPS status
    boolean canGetLocation = false;
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    // Declaring a Location Manager
    protected LocationManager locationManager;
    public Location getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled) {
                Log.d("GPS Enabled!EEEEEE", "GPS Enabled");
            } else {
                this.canGetLocation = true;

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, location.getLatitude() + "", Toast.LENGTH_LONG);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
