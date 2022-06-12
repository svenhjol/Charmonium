package svenhjol.charmonium.module.situational_ambience;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.ClientModule;
import svenhjol.charmonium.annotation.Config;
import svenhjol.charmonium.api.event.AddCaveAmbienceCheck;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.loader.CharmModule;
import svenhjol.charmonium.module.situational_ambience.sounds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ClientModule(mod = Charmonium.MOD_ID, description = "Plays specific ambient sound according to the situation or location.")
public class SituationalAmbience extends CharmModule {
    public Handler handler;

    public final static int CAVE_LIGHT_LEVEL = 10;

    public static List<ResourceLocation> VALID_CAVE_DIMENSIONS = new ArrayList<>();

    @Config(name = "Volume scaling", description = "Affects the volume of all situational ambient sounds. 1.0 is full volume.")
    public static float volumeScaling = 0.55F;

    @Config(name = "Above Ground For Ambience Silencing", description = "Number of blocks above the ground that situational ambience will be silenced.")
    public static int cullSoundAboveGround = 32;

    @Config(name = "Alien ambience", description = "If true, plays ambient sounds while anywhere in the End.")
    public static boolean alien = true;

    @Config(name = "Bleak ambience", description = "If true, plays ambient sounds in cold and/or barren overworld environments.")
    public static boolean bleak = true;

    @Config(name = "Cave drone ambience", description = "If true, plays a low drone sound when in a cave below Y 48.")
    public static boolean caveDrone = true;

    @Config(name = "Cave depth ambience", description = "If true, plays more intense cave sounds when below Y 0 and light level is lower than 10.")
    public static boolean caveDepth = true;

    @Config(name = "Deepslate ambience", description = "If true, plays ambient sounds when the player is underground and near deepslate blocks.")
    public static boolean deepslate = true;

    @Config(name = "Dry ambience", description = "If true, plays ambient sounds in dry and/or hot overworld environments.")
    public static boolean dry = true;

    @Config(name = "Geode ambience", description = "If true, plays ambient sounds from a nearby amethyst geode.")
    public static boolean geode = true;

    @Config(name = "Gravel ambience", description = "If true, plays ambient sounds when the player is underground and near gravel blocks.")
    public static boolean gravel = true;

    @Config(name = "High ambience", description = "If true, plays ambient sounds when high up in the overworld.")
    public static boolean high = true;

    @Config(name = "Mansion ambience", description = "If true, plays ambient sounds while inside a woodland mansion.")
    public static boolean mansion = true;

    @Config(name = "Mineshaft ambience", description = "If true, plays ambient sounds from a nearby mineshaft.")
    public static boolean mineshaft = true;

    @Config(name = "Night plains ambience", description = "If true, plays ambient sounds in plains environments at night.")
    public static boolean nightPlains = true;

    @Config(name = "Snowstorm ambience", description = "If true, plays ambient sounds when in a cold biome during a thunderstorm.")
    public static boolean snowstorm = true;

    @Config(name = "Underground water ambience", description = "If true, plays water sounds from a nearby water source when underground.")
    public static boolean undergroundWater = true;

    @Config(name = "Village ambience", description = "If true, plays ambient sounds when a player is inside a village.")
    public static boolean village = true;

    @Config(name = "Valid cave drone dimensions", description = "Dimensions in which cave drone and cave depth sounds will be played.")
    public static List<String> configCaveDimensions = Arrays.asList(
        "minecraft:overworld"
    );

    @Override
    public void register() {
        Alien.register();
        Bleak.register();
        CaveDepth.register();
        CaveDrone.register();
        UndergroundWater.register();
        Deepslate.register();
        Dry.register();
        Geode.register();
        Gravel.register();
        High.register();
        Mansion.register();
        Mineshaft.register();
        NightPlains.register();
        Snowstorm.register();
        Village.register();
    }

    @Override
    public void runWhenEnabled() {
        ClientEntityEvents.ENTITY_LOAD.register(this::handleEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(this::handleEntityUnload);
        ClientTickEvents.END_CLIENT_TICK.register(this::handleClientTick);

        configCaveDimensions.forEach(dim -> VALID_CAVE_DIMENSIONS.add(new ResourceLocation(dim)));
    }

    private void handleEntityLoad(Entity entity, Level level) {
        if (entity instanceof Player) {
            var result = AddCaveAmbienceCheck.EVENT.invoker().interact(level);
            var id = level.dimension().location();

            if (result && !VALID_CAVE_DIMENSIONS.contains(id)) {
                VALID_CAVE_DIMENSIONS.add(id);
            }

            trySetupSoundHandler((Player) entity);
        }
    }

    private void handleEntityUnload(Entity entity, Level level) {
        if (entity instanceof LocalPlayer && handler != null) {
            handler.stop();
        }
    }

    private void handleClientTick(Minecraft client) {
        if (handler != null && !client.isPaused()) {
            handler.tick();
        }
    }

    public void trySetupSoundHandler(Player player) {
        if (!(player instanceof LocalPlayer)) return;

        if (handler == null) {
            handler = new Handler(player);
        }

        handler.updatePlayer(player);
    }

    public static class Handler extends SoundHandler<SituationalSound> {
        public Handler(@NotNull Player player) {
            super(player);

            if (alien) Alien.init(this);
            if (bleak) Bleak.init(this);
            if (caveDepth) CaveDepth.init(this);
            if (caveDrone) CaveDrone.init(this);
            if (undergroundWater) UndergroundWater.init(this);
            if (deepslate) Deepslate.init(this);
            if (dry) Dry.init(this);
            if (geode) Geode.init(this);
            if (gravel) Gravel.init(this);
            if (high) High.init(this);
            if (mansion) Mansion.init(this);
            if (mineshaft) Mineshaft.init(this);
            if (nightPlains) NightPlains.init(this);
            if (snowstorm) Snowstorm.init(this);
            if (village) Village.init(this);
        }
    }
}
