package svenhjol.charmonium.forge;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import svenhjol.charm_core.forge.base.BaseForgeClientInitializer;
import svenhjol.charmonium.Charmonium;

public class ForgeClientModInitializer {
    private final Charmonium mod;
    public static final ClientInitializer INIT = new ClientInitializer();

    public ForgeClientModInitializer() {
        var modEventBus = INIT.getModEventBus();
        modEventBus.addListener(this::handleClientSetup);

        mod = new Charmonium(INIT);
    }

    private void handleClientSetup(FMLClientSetupEvent event) {
        mod.run();
    }

    public static class ClientInitializer extends BaseForgeClientInitializer {
        @Override
        public String getNamespace() {
            return Charmonium.MOD_ID;
        }
    }
}
