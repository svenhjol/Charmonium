package svenhjol.charmonium.feature.world_ambience.client;

import net.minecraft.resources.ResourceLocation;
import svenhjol.charmonium.charmony.event.ClientEntityJoinEvent;
import svenhjol.charmonium.charmony.event.ClientEntityLeaveEvent;
import svenhjol.charmonium.charmony.event.ClientTickEvent;
import svenhjol.charmonium.charmony.feature.RegisterHolder;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmonium.feature.world_ambience.sounds.*;
import svenhjol.charmonium.sound.SoundType;

import java.util.ArrayList;
import java.util.List;

public final class Registers extends RegisterHolder<WorldAmbience> {
    public final List<ResourceLocation> validCaveDimensions = new ArrayList<>();
    public final SoundType<WorldSound> alien = new Alien();
    public final SoundType<WorldSound> bleak = new Bleak();
    public final SoundType<WorldSound> caveDrone = new CaveDrone();
    public final SoundType<WorldSound> caveDepth = new CaveDepth();
    public final SoundType<WorldSound> deepslate = new Deepslate();
    public final SoundType<WorldSound> dry = new Dry();
    public final SoundType<WorldSound> geode = new Geode();
    public final SoundType<WorldSound> gravel = new Gravel();
    public final SoundType<WorldSound> high = new High();
    public final SoundType<WorldSound> mansion = new Mansion();
    public final SoundType<WorldSound> mineshaft = new Mineshaft();
    public final SoundType<WorldSound> nightPlains = new NightPlains();
    public final SoundType<WorldSound> snowstorm = new Snowstorm();
    public final SoundType<WorldSound> undergroundWater = new UndergroundWater();
    public final SoundType<WorldSound> village = new Village();

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
