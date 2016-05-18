package si.lj.uni.fri.ips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WifiListAdapter extends ArrayAdapter<FakeScanResult> {

    private final Context context;
    private final FakeScanResult[] values;

    public WifiListAdapter(Context context, FakeScanResult[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FakeScanResult scanResult = values[position];

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View wifiRowView = inflater.inflate(R.layout.wifi_data_row, parent, false);

        ((TextView) wifiRowView.findViewById(R.id.wifiSSID)).setText("SSID: " + scanResult.getSSID());
        ((TextView) wifiRowView.findViewById(R.id.wifiBSSID)).setText("BSSID: " + scanResult.getBSSID());
        ((TextView) wifiRowView.findViewById(R.id.wifiRSSI)).setText("RSSI: " + String.valueOf(scanResult.getLevel()) + "dBm");

        return wifiRowView;
    }
}
