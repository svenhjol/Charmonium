package svenhjol.charmonium;

import svenhjol.charmony.base.DefaultClientMod;

public class CharmoniumClient extends DefaultClientMod {
    public static final String MOD_ID = "charmonium";
    private static CharmoniumClient instance;

    public static CharmoniumClient instance() {
        if (instance == null) {
            instance = new CharmoniumClient();
        }
        return instance;
    }

    @Override
    public String modId() {
        return MOD_ID;
    }
}
