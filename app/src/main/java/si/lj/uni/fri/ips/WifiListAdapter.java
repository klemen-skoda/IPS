package si.lj.uni.fri.ips;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WifiListAdapter extends BaseAdapter {

    private final Context context;
    private ScanResult[] values;

    public WifiListAdapter(Context context, ScanResult[] values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return values[position].level;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScanResult scanResult = values[position];

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View wifiRowView = inflater.inflate(R.layout.wifi_data_row, parent, false);

        ((TextView) wifiRowView.findViewById(R.id.wifiSSID)).setText("SSID: " + scanResult.SSID);
        ((TextView) wifiRowView.findViewById(R.id.wifiBSSID)).setText("BSSID: " + scanResult.BSSID);
        ((TextView) wifiRowView.findViewById(R.id.wifiRSSI)).setText("RSSI: " + scanResult.level + "dBm");

        return wifiRowView;
    }

    public void setScanResults(ScanResult[] values) {
        this.values = values;
        notifyDataSetChanged();
    }
}