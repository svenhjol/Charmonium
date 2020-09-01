package svenhjol.charmonium;

import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.charmonium.module.Music;
import svenhjol.charmonium.module.Sounds;
import svenhjol.meson.MesonMod;
import svenhjol.meson.MesonModule;

import java.util.Arrays;
import java.util.List;

public class Charmonium extends MesonMod {
    public static final String MOD_ID = "charmonium";

    @Override
    public void onInitialize() {
        super.init(MOD_ID);
        CharmoniumSounds.init(this);
    }

    @Override
    public List<Class<? extends MesonModule>> getModules() {
        return Arrays.asList(
            Music.class,
            Sounds.class
        );
    }
}
