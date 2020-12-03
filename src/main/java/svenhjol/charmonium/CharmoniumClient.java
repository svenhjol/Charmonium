package svenhjol.charmonium;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charm.base.handler.ClientHandler;

public class CharmoniumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientHandler.INSTANCE.registerFabricMod(Charmonium.MOD_ID);
    }
}
