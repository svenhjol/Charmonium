package svenhjol.charmonium.fabric;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charm_core.fabric.base.BaseFabricClientInitializer;
import svenhjol.charm_core.fabric.common.CommonRegistry;
import svenhjol.charmonium.Charmonium;

public class FabricClientModInitializer implements ClientModInitializer {
    public static final Initializer INIT = new Initializer();
    private static Charmonium mod;

    @Override
    public void onInitializeClient() {
        // Always init Core first.
        svenhjol.charm_core.fabric.FabricClientModInitializer.initCharmCoreClient();

        if (mod == null) {
            mod = new Charmonium(INIT);
            mod.run();
        }
    }

    public static class Initializer extends BaseFabricClientInitializer {
        @Override
        public CommonRegistry getCommonRegistry() {
            return null; // No common registry with client-only mods.
        }

        @Override
        public String getNamespace() {
            return Charmonium.MOD_ID;
        }
    }
}
