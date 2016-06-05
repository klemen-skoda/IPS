package si.lj.uni.fri.ips;

import java.util.Arrays;
import java.util.List;

public final class AccessPoints {

    private static final AccessPoint ACCESS_POINT_A = new AccessPoint("58:6d:8f:62:e9:bf", 100, 133, 0);
    private static final AccessPoint ACCESS_POINT_B = new AccessPoint("f4:f2:6d:a0:23:f8", 200, 222, 0);
    private static final AccessPoint ACCESS_POINT_C = new AccessPoint("e0:91:f5:e7:1e:1a", 212, 222, 1);
    private static final AccessPoint ACCESS_POINT_D = new AccessPoint("c8:b3:73:31:d2:0d", 5, 4, 0); // Swireless
    private static final AccessPoint ACCESS_POINT_E = new AccessPoint("dc:fe:07:1b:34:86", 0, 0, 1); // 270072
    private static final AccessPoint ACCESS_POINT_F = new AccessPoint("00:0e:2e:40:87:fd", 0, 6, 1); // default jaht
    private static final AccessPoint ACCESS_POINT_G = new AccessPoint("68:7f:74:4c:95:1b", 60, 15, 1); // linksys

    public static List<AccessPoint> getAccessPoints() {
        return Arrays.asList(new AccessPoint[]{ACCESS_POINT_A, ACCESS_POINT_B, ACCESS_POINT_C, ACCESS_POINT_D ,ACCESS_POINT_E, ACCESS_POINT_F, ACCESS_POINT_G});
    }
}