package si.lj.uni.fri.ips;

import java.util.Arrays;
import java.util.List;

public final class AccessPoints {

    private static final AccessPoint ACCESS_POINT_A = new AccessPoint("58:6d:8f:62:e9:bf", 100, 133, 0);
    private static final AccessPoint ACCESS_POINT_B = new AccessPoint("f4:f2:6d:a0:23:f8", 200, 222, 0);
    private static final AccessPoint ACCESS_POINT_C = new AccessPoint("e0:91:f5:e7:1e:1a", 212, 222, 1);

    public static List<AccessPoint> getAccessPoints() {
        return Arrays.asList(new AccessPoint[]{ACCESS_POINT_A, ACCESS_POINT_B, ACCESS_POINT_C});
    }
}