package svenhjol.charmonium.module;

import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmModule;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.client.AmbientMusicClient;
import svenhjol.charm.base.iface.Module;



@Module(mod = Charmonium.MOD_ID, description = "Adds custom music tracks that play in certain situations.")
public class Music extends CharmModule {
    public static AmbientMusicClient client;

    @Override
    public void clientInit() {
        client = new AmbientMusicClient();
    }
}

