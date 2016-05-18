package si.lj.uni.fri.ips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class WifiList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);

        final WifiListAdapter wifiListAdapter = new WifiListAdapter(this, getAvailableWifiList());
        final ListView wifiListView = (ListView) findViewById(R.id.wifiList);

        if (wifiListView != null) {
            wifiListView.setAdapter(wifiListAdapter);
        }
    }

    private FakeScanResult[] getAvailableWifiList() {
        return new FakeScanResult[] {
                new FakeScanResult("eduroam", "00:19:3b:99:e2:80", 20),
                new FakeScanResult("guest", "c0:0f:c5:1a:3a:a0", 32),
                new FakeScanResult("Zelena Prestolnica Evrope", "89:e1:39:46:e1:b1", 14),
                new FakeScanResult("WiFreeLjubljana", "c8:58:ef:84:8d:7b", 25),
                new FakeScanResult("IEcom-Amis", "d8:7c:8d:04:16:02", 14),
                new FakeScanResult("Hidden Network", "7e:3d:4e:c7:be:4d", 45),
                new FakeScanResult("InternetInstitute", "d2:94:17:35:70:c0", 96),
                new FakeScanResult("conference_room", "78:e4:cd:ab:d1:37", 25),
                new FakeScanResult("Home Network", "24:99:8d:82:8b:1a", 14)
        };
    }
}