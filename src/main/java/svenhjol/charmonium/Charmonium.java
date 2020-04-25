package svenhjol.charmonium;

import net.minecraftforge.fml.common.Mod;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.meson.MesonInstance;
import svenhjol.meson.handler.LogHandler;

@Mod(Charmonium.MOD_ID)
public class Charmonium extends MesonInstance {
    public static final String MOD_ID = "charmonium";
    public static final LogHandler LOG = new LogHandler(Charmonium.MOD_ID);

    public Charmonium() {
        super(Charmonium.MOD_ID, LOG);

        CharmoniumSounds.init(this);
    }
}
