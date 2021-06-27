package svenhjol.charmonium;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmonium.handler.LogHandler;
import svenhjol.charmonium.init.CharmoniumLoader;

public class Charmonium implements ClientModInitializer {
    public static final String MOD_ID = "charmonium";
    public static final LogHandler LOG = new LogHandler("Charmonium");
    public static CharmoniumLoader loader;

    @Override
    public void onInitializeClient() {
        loader = new CharmoniumLoader(MOD_ID);
    }

    public static boolean isEnabled(String module) {
        return CharmoniumLoader.isEnabled(module);
    }
}
