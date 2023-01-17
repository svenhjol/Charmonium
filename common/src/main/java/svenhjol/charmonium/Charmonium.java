package svenhjol.charmonium;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charm_core.annotation.ClientFeature;
import svenhjol.charm_core.iface.*;

public class Charmonium {
    public static final String MOD_ID = "charmonium";
    public static final String PREFIX = "svenhjol." + MOD_ID;
    public static final String FEATURE_PREFIX = PREFIX + ".feature";

    public static ILog LOG;
    public static ILoader LOADER;
    public static IClientRegistry REGISTRY;

    public Charmonium(IClientInitializer init) {
        LOG = init.getLog();
        LOADER = init.getLoader();
        REGISTRY = init.getRegistry();

        LOADER.init(FEATURE_PREFIX, ClientFeature.class);
    }

    public void run() {
        LOADER.run();
    }

    public static ResourceLocation makeId(String id) {
        return !id.contains(":") ? new ResourceLocation(MOD_ID, id) : new ResourceLocation(id);
    }
}
