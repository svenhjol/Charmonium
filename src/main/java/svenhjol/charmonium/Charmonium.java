package svenhjol.charmonium;

import svenhjol.charmonium.feature.ambience_settings.AmbienceSettings;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmony.client.ClientFeature;
import svenhjol.charmony.client.ClientMod;

import java.util.List;

public class Charmonium extends ClientMod {
    public static final String ID = "charmonium";

    @Override
    public String modId() {
        return ID;
    }

    @Override
    public List<Class<? extends ClientFeature>> features() {
        return List.of(
            AmbienceSettings.class,
            BiomeAmbience.class,
            WorldAmbience.class
        );
    }
}
