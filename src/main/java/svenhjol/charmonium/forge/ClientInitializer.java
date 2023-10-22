package svenhjol.charmonium.forge;

import svenhjol.charmonium.Charmonium;
import svenhjol.charmony.base.Mods;

public class ClientInitializer {
    public ClientInitializer() {
        var instance = Mods.client(Charmonium.ID, Charmonium::new);
        var loader = instance.loader();

        // Autoload all annotated client features from the feature namespace.
        loader.init(instance.features());
    }
}
