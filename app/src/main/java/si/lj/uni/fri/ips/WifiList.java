package si.lj.uni.fri.ips;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class WifiList extends Activity {

    private WifiManager wifi;
    private WifiListAdapter wifiListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);

        final ListView wifiListView = (ListView) findViewById(R.id.wifiList);
        wifiListAdapter = new WifiListAdapter(this, getAvailableWifiList());

        if (wifiListView != null) {
            wifiListView.setAdapter(wifiListAdapter);
        }
    }

    private ScanResult[] getAvailableWifiList() {
        final List<ScanResult> results = new ArrayList<>();

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == false) {
            wifi.setWifiEnabled(true);
        }
        wifi.startScan();
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                results.clear();
                results.addAll(wifi.getScanResults());
                wifiListAdapter.setScanResults(results.toArray(new ScanResult[results.size()]));
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        return results.toArray(new ScanResult[results.size()]);
    }
}