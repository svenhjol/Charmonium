package svenhjol.charmonium.fabric;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.charmony.client.ClientLoader;

public final class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        var loader = ClientLoader.create(Charmonium.ID);
        loader.setup(Charmonium.features());
        loader.run();
    }
}
