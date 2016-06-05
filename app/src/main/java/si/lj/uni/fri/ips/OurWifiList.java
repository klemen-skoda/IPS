package si.lj.uni.fri.ips;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OurWifiList extends Activity {

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
                List<AccessPoint> accessPoints = AccessPoints.getAccessPoints();
                for (ScanResult scanResult : wifi.getScanResults()) {
                    for (AccessPoint accessPoint : accessPoints) {
                        if (scanResult.BSSID.equalsIgnoreCase(accessPoint.getBSSID())) {
                            results.add(scanResult);
                            accessPoint.setRSSI(scanResult.level);
                        }
                    }
                }
                wifiListAdapter.setScanResults(results.toArray(new ScanResult[results.size()]));
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        return results.toArray(new ScanResult[results.size()]);
    }
}