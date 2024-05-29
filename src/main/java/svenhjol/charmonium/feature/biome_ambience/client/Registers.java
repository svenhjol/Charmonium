package svenhjol.charmonium.feature.biome_ambience.client;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmonium.charmony.event.ClientEntityJoinEvent;
import svenhjol.charmonium.charmony.event.ClientEntityLeaveEvent;
import svenhjol.charmonium.charmony.event.ClientTickEvent;
import svenhjol.charmonium.charmony.feature.RegisterHolder;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;
import svenhjol.charmonium.feature.biome_ambience.sounds.*;
import svenhjol.charmonium.sound.SoundType;

import java.util.ArrayList;
import java.util.List;

public final class Registers extends RegisterHolder<BiomeAmbience> {
    public final List<ResourceLocation> validDimensions = new ArrayList<>();

    public final SoundType<BiomeSound> badlands = new Badlands();
    public final SoundType<BiomeSound> beach = new Beach();
    public final SoundType<BiomeSound> caves = new Caves();
    public final SoundType<BiomeSound> desert = new Desert();
    public final SoundType<BiomeSound> forest = new Forest();
    public final SoundType<BiomeSound> icy = new Icy();
    public final SoundType<BiomeSound> jungle = new Jungle();
    public final SoundType<BiomeSound> mountains = new Mountains();
    public final SoundType<BiomeSound> ocean = new Ocean();
    public final SoundType<BiomeSound> plains = new Plains();
    public final SoundType<BiomeSound> river = new River();
    public final SoundType<BiomeSound> savanna = new Savanna();
    public final SoundType<BiomeSound> swamp = new Swamp();
    public final SoundType<BiomeSound> taiga = new Taiga();
    public final SoundType<BiomeSound> theEnd = new TheEnd();

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
