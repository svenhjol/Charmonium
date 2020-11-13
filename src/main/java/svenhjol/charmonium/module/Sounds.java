package svenhjol.charmonium.module;

import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Config;
import svenhjol.charm.base.iface.Module;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.client.SoundsClient;

@Module(mod = Charmonium.MOD_ID, client = SoundsClient.class, description = "Ambient sounds play according to the biome, time of day and depth below surface.")
public class Sounds extends CharmModule {

    @Config(name = "Volume multiplier", description = "Volume of ambient sounds is multiplied by this amount.")
    public static double volumeMultiplier = 1.0D;
}
