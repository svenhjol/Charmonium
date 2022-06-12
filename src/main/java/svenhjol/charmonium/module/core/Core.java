package svenhjol.charmonium.module.core;

import net.minecraft.sounds.SoundSource;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.ClientModule;
import svenhjol.charmonium.annotation.Config;
import svenhjol.charmonium.helper.LogHelper;
import svenhjol.charmonium.loader.CharmModule;

import java.util.Arrays;

@ClientModule(mod = Charmonium.MOD_ID, alwaysEnabled = true)
public class Core extends CharmModule {
    @Config(name = "Audio channel", description = "The channel that Charmonium will use for playing sounds. Defaults to 'ambient'.\n" +
        "Options: music, record, weather, block, hostile, neutral, player, ambient, voice")
    public static String channel = "ambient";

    private static SoundSource source;

    @Override
    public void register() {
        var key = channel.toLowerCase();
        source = Arrays.stream(SoundSource.values())
            .filter(v -> v.getName().equals(key))
            .findFirst()
            .orElse(SoundSource.AMBIENT);

        LogHelper.info(getClass(), "Using sound channel/source: " + source.getName());
    }

    public static SoundSource getSource() {
        return source;
    }
}
