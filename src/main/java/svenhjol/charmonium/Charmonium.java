package svenhjol.charmonium;

import net.fabricmc.api.ModInitializer;
import svenhjol.charm.base.handler.ModuleHandler;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.charmonium.module.Music;
import svenhjol.charmonium.module.Sounds;

import java.util.Arrays;

public class Charmonium implements ModInitializer {
    public static final String MOD_ID = "charmonium";

    @Override
    public void onInitialize() {
        ModuleHandler.INSTANCE.registerFabricMod(MOD_ID, Arrays.asList(
            Music.class,
            Sounds.class
        ));

        CharmoniumSounds.init();
    }
}
