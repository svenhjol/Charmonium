package svenhjol.charmonium.feature.ambience_settings;

import net.minecraft.sounds.SoundSource;
import svenhjol.charm_core.annotation.ClientFeature;
import svenhjol.charm_core.annotation.Configurable;
import svenhjol.charm_core.base.CharmFeature;
import svenhjol.charmonium.Charmonium;

import java.util.Arrays;

@ClientFeature(mod = Charmonium.MOD_ID)
public class AmbienceSettings extends CharmFeature {
    @Configurable(name = "Audio channel", description = "The channel that Charmonium will use for playing sounds. Defaults to 'ambient'.\n" +
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

        Charmonium.LOG.info(getClass(), "Using sound channel/source: " + source.getName());
    }

    public static SoundSource getAudioChannel() {
        return source;
    }
}
