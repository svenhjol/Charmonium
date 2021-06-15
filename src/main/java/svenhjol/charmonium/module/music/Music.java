package svenhjol.charmonium.module.music;

import svenhjol.charm.annotation.Module;
import svenhjol.charm.module.CharmModule;
import svenhjol.charmonium.Charmonium;

@Module(mod = Charmonium.MOD_ID, client = MusicClient.class, description = "Adds custom music tracks that play in certain situations.")
public class Music extends CharmModule {
}

