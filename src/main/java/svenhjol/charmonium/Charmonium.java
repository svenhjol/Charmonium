package svenhjol.charmonium;

import svenhjol.charmony.base.DefaultClientMod;

public class Charmonium extends DefaultClientMod {
    public static final String MOD_ID = "charmonium";
    private static Charmonium instance;

    public static Charmonium instance() {
        if (instance == null) {
            instance = new Charmonium();
        }
        return instance;
    }

    @Override
    public String modId() {
        return MOD_ID;
    }
}
