package svenhjol.charmonium.fabric;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmony.fabric.CharmonyModLoader;

public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CharmonyModLoader.clientMods("charmonium");
    }
}
