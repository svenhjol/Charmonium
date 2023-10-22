package svenhjol.charmonium.fabric;

import net.fabricmc.api.ClientModInitializer;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmony.base.Mods;

public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        svenhjol.charmony.fabric.ClientInitializer.initCharmony();

        var instance = Mods.client(Charmonium.ID, Charmonium::new);
        var loader = instance.loader();

        loader.init(instance.features());
        loader.run();
    }
}
