package svenhjol.charmonium.feature.ambience_settings.client;

import net.minecraft.sounds.SoundSource;
import svenhjol.charmonium.charmony.feature.RegisterHolder;
import svenhjol.charmonium.feature.ambience_settings.AmbienceSettings;

import java.util.Arrays;

public final class Registers extends RegisterHolder<AmbienceSettings> {
    public final SoundSource source;

    public Registers(AmbienceSettings feature) {
        super(feature);

        var key = feature.channel().toLowerCase();
        source = Arrays.stream(SoundSource.values())
            .filter(v -> v.getName().equals(key))
            .findFirst()
            .orElse(SoundSource.AMBIENT);

        log().info("Using sound channel/source: " + source.getName());
    }
}
