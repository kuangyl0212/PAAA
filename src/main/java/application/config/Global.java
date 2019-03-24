package application.config;

public class Global {
    private static LANG LAN = LANG.PYTHON;

    public static void setLAN(LANG LAN) {
        Global.LAN = LAN;
    }

    public static LANG getLAN() {
        return LAN;
    }
}
