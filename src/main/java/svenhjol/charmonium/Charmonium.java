package svenhjol.charmonium;

import net.fabricmc.api.ModInitializer;
import svenhjol.charm.Charm;
import svenhjol.charm.init.CharmLoader;
import svenhjol.charmonium.init.CharmoniumSounds;

public class Charmonium implements ModInitializer {
    public static final String MOD_ID = "charmonium";

    @Override
    public void onInitialize() {
        Charm.runFirst();

        new CharmLoader(MOD_ID);

        CharmoniumSounds.init();
    }
}
