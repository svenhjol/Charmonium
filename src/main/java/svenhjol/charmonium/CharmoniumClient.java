package svenhjol.charmonium;

import svenhjol.charmonium.feature.ambience_settings.AmbienceSettings;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmony.base.CharmonyFeature;
import svenhjol.charmony.base.DefaultClientMod;

import java.util.List;

public class CharmoniumClient extends DefaultClientMod {
    public static final String MOD_ID = "charmonium";
    private static CharmoniumClient instance;

    public static CharmoniumClient instance() {
        if (instance == null) {
            instance = new CharmoniumClient();
        }
        return instance;
    }

    @Override
    public String modId() {
        return MOD_ID;
    }

    @Override
    public List<Class<? extends CharmonyFeature>> features() {
        return List.of(
            AmbienceSettings.class,
            BiomeAmbience.class,
            WorldAmbience.class
        );
    }
}
