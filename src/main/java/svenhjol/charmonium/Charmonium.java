package svenhjol.charmonium;

import net.fabricmc.api.ModInitializer;
import svenhjol.charm.base.handler.ModuleHandler;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.charmonium.module.Music;
import svenhjol.charmonium.module.Sounds;


import java.util.ArrayList;
import java.util.Arrays;


public class Charmonium implements ModInitializer {
    public static final String MOD_ID = "charmonium";

    @Override
    public void onInitialize() {
        CharmoniumSounds.init();


        ModuleHandler.AVAILABLE_MODULES.put(Charmonium.MOD_ID, new ArrayList(Arrays.asList(
                Music.class,
                Sounds.class
        )));
    }
}
