package svenhjol.charmonium.fabric;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.charmony.client.ClientLoader;

public final class ClientInitializer implements ClientModInitializer {
    private static boolean initialized = false;

    @Override
    public void onInitializeClient() {
        initCharm();
    }

    public static void initCharm() {
        if (initialized) return;

        var loader = ClientLoader.create(Charmonium.ID);
        loader.setup(Charmonium.features());
        loader.run();

        initialized = true;
    }
}
