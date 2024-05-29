package svenhjol.charmonium.feature.world_ambience.client;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmonium.charmony.event.ClientEntityJoinEvent;
import svenhjol.charmonium.charmony.event.ClientEntityLeaveEvent;
import svenhjol.charmonium.charmony.event.ClientTickEvent;
import svenhjol.charmonium.charmony.feature.RegisterHolder;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmonium.feature.world_ambience.sounds.*;
import svenhjol.charmonium.sound.ISoundType;

import java.util.ArrayList;
import java.util.List;

public final class Registers extends RegisterHolder<WorldAmbience> {
    public final List<ResourceLocation> validCaveDimensions = new ArrayList<>();
    public final ISoundType<WorldSound> ALIEN = new Alien();
    public final ISoundType<WorldSound> BLEAK = new Bleak();
    public final ISoundType<WorldSound> CAVE_DRONE = new CaveDrone();
    public final ISoundType<WorldSound> CAVE_DEPTH = new CaveDepth();
    public final ISoundType<WorldSound> DEEPSLATE = new Deepslate();
    public final ISoundType<WorldSound> DRY = new Dry();
    public final ISoundType<WorldSound> GEODE = new Geode();
    public final ISoundType<WorldSound> GRAVEL = new Gravel();
    public final ISoundType<WorldSound> HIGH = new High();
    public final ISoundType<WorldSound> MANSION = new Mansion();
    public final ISoundType<WorldSound> MINESHAFT = new Mineshaft();
    public final ISoundType<WorldSound> NIGHT_PLAINS = new NightPlains();
    public final ISoundType<WorldSound> SNOWSTORM = new Snowstorm();
    public final ISoundType<WorldSound> UNDERGROUND_WATER = new UndergroundWater();
    public final ISoundType<WorldSound> VILLAGE = new Village();

    public Registers(WorldAmbience feature) {
        super(feature);

        feature().caveDimensions().forEach(dim -> validCaveDimensions.add(ResourceLocation.parse(dim)));
    }

    @Override
    public void onEnabled() {
        ClientEntityJoinEvent.INSTANCE.handle(feature().handlers::handleClientEntityJoin);
        ClientEntityLeaveEvent.INSTANCE.handle(feature().handlers::handleClientEntityLeave);
        ClientTickEvent.INSTANCE.handle(feature().handlers::handleClientTick);
    }
}
