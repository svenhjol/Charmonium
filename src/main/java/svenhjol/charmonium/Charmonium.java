package svenhjol.charmonium;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmonium.charmony.client.ClientFeature;
import svenhjol.charmonium.feature.ambience_settings.AmbienceSettings;

import java.util.List;

public final class Charmonium {
    public static final String ID = "charmonium";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(ID, path);
    }

    public static List<Class<? extends ClientFeature>> features() {
        return List.of(
            AmbienceSettings.class
        );
    }
}
