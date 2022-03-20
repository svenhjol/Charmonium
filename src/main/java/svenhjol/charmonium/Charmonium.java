package svenhjol.charmonium;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmonium.init.CharmoniumBiomes;
import svenhjol.charmonium.init.CharmoniumLog;
import svenhjol.charmonium.init.CharmoniumPacks;
import svenhjol.charmonium.loader.CharmModule;
import svenhjol.charmonium.loader.ClientLoader;

public class Charmonium implements ClientModInitializer {
    public static final String MOD_ID = "charmonium";
    public static ClientLoader<CharmModule> LOADER = new ClientLoader<>(MOD_ID, "svenhjol.charmonium.module");

    @Override
    public void onInitializeClient() {
        CharmoniumLog.init();
        CharmoniumBiomes.init();
        CharmoniumPacks.init();
        LOADER.init();
    }
}
