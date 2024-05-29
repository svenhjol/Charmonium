package svenhjol.charmonium;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmonium.charmony.client.ClientFeature;
import svenhjol.charmonium.feature.ambience_settings.AmbienceSettings;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;

import java.util.List;

public final class Charmonium {
    public static final String ID = "charmonium";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(ID, path);
    }

    public static List<Class<? extends ClientFeature>> features() {
        return List.of(
            AmbienceSettings.class,
            BiomeAmbience.class,
            WorldAmbience.class
        );
    }
}
