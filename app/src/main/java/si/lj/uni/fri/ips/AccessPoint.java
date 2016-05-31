package si.lj.uni.fri.ips;

public class AccessPoint {

    private String bssid;
    private int x;
    private int y;
    private int z; // floor ( 1, 2, 3,...)

    public AccessPoint(String bssid, int x, int y, int z) {
        this.bssid = bssid;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getBSSID() {
        return bssid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}