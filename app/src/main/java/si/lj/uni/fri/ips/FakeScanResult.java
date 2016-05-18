package si.lj.uni.fri.ips;

public class FakeScanResult {

    private String ssid;
    private String bssid;
    private int rssi;

    public FakeScanResult(String ssid, String bssid, int rssi) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rssi = rssi;
    }

    public String getSSID() {
        return ssid;
    }

    public String getBSSID() {
        return bssid;
    }

    public int getLevel() {
        return rssi;
    }
}