package svenhjol.charmonium.feature.biome_ambience.client;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmonium.charmony.event.ClientEntityJoinEvent;
import svenhjol.charmonium.charmony.event.ClientEntityLeaveEvent;
import svenhjol.charmonium.charmony.event.ClientTickEvent;
import svenhjol.charmonium.charmony.feature.RegisterHolder;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;
import svenhjol.charmonium.feature.biome_ambience.sounds.*;
import svenhjol.charmonium.sound.ISoundType;

import java.util.ArrayList;
import java.util.List;

public final class Registers extends RegisterHolder<BiomeAmbience> {
    public final List<ResourceLocation> validDimensions = new ArrayList<>();

    public final ISoundType<BiomeSound> BADLANDS = new Badlands();
    public final ISoundType<BiomeSound> BEACH = new Beach();
    public final ISoundType<BiomeSound> CAVES = new Caves();
    public final ISoundType<BiomeSound> DESERT = new Desert();
    public final ISoundType<BiomeSound> FOREST = new Forest();
    public final ISoundType<BiomeSound> ICY = new Icy();
    public final ISoundType<BiomeSound> JUNGLE = new Jungle();
    public final ISoundType<BiomeSound> MOUNTAINS = new Mountains();
    public final ISoundType<BiomeSound> OCEAN = new Ocean();
    public final ISoundType<BiomeSound> PLAINS = new Plains();
    public final ISoundType<BiomeSound> RIVER = new River();
    public final ISoundType<BiomeSound> SAVANNA = new Savanna();
    public final ISoundType<BiomeSound> SWAMP = new Swamp();
    public final ISoundType<BiomeSound> TAIGA = new Taiga();
    public final ISoundType<BiomeSound> THE_END = new TheEnd();

    public Registers(BiomeAmbience feature) {
        super(feature);

        feature().dimensions().forEach(dim -> validDimensions.add(ResourceLocation.parse(dim)));
    }

    @Override
    public void onEnabled() {
        ClientEntityJoinEvent.INSTANCE.handle(feature().handlers::clientEntityJoin);
        ClientEntityLeaveEvent.INSTANCE.handle(feature().handlers::clientEntityLeave);
        ClientTickEvent.INSTANCE.handle(feature().handlers::clientTick);
    }
}
