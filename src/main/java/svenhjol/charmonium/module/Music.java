package svenhjol.charmonium.module;

import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Module;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.client.MusicClient;

@Module(mod = Charmonium.MOD_ID, client = MusicClient.class, description = "Adds custom music tracks that play in certain situations.")
public class Music extends CharmModule {
}

