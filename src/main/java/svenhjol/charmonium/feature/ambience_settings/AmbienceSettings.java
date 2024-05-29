package svenhjol.charmonium.feature.ambience_settings;

import net.minecraft.sounds.SoundSource;
import svenhjol.charmonium.charmony.annotation.Configurable;
import svenhjol.charmonium.charmony.annotation.Feature;
import svenhjol.charmonium.charmony.client.ClientFeature;
import svenhjol.charmonium.charmony.client.ClientLoader;
import svenhjol.charmonium.feature.ambience_settings.client.Registers;

@Feature(description = "Settings for how ambient sounds are played.")
public final class AmbienceSettings extends ClientFeature {
    public final Registers registers;

    @Configurable(name = "Audio channel", description = "The channel that Charmonium will use for playing sounds. Defaults to 'ambient'.\n" +
        "Options: music, record, weather, block, hostile, neutral, player, ambient, voice")
    private static String channel = "ambient";

    public AmbienceSettings(ClientLoader loader) {
        super(loader);

        registers = new Registers(this);
    }

    public String channel() {
        return channel;
    }

    public SoundSource source() {
        return registers.source;
    }
}
