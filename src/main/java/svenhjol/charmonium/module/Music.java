package svenhjol.charmonium.module;

import svenhjol.charmonium.client.AmbientMusicClient;
import svenhjol.meson.MesonMod;
import svenhjol.meson.MesonModule;
import svenhjol.meson.iface.Module;

@Module(description = "Adds custom music tracks that play in certain situations.")
public class Music extends MesonModule {
    public static AmbientMusicClient client;

    @Override
    public void clientInit() {
        client = new AmbientMusicClient();
    }
}
