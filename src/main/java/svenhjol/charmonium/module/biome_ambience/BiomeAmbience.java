package svenhjol.charmonium.module.biome_ambience;

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
import svenhjol.charmonium.api.event.AddBiomeAmbienceCallback;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.loader.CharmModule;
import svenhjol.charmonium.module.biome_ambience.sounds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ClientModule(mod = Charmonium.MOD_ID, description = "Plays ambient background sound according to the biome and time of day.")
public class BiomeAmbience extends CharmModule {
    public Handler handler;

    public static List<ResourceLocation> VALID_DIMENSIONS = new ArrayList<>();

    @Config(name = "Volume scaling", description = "Affects the volume of all biome ambient sounds. 1.0 is full volume.")
    public static float volumeScaling = 0.55F;

    @Config(name = "Above ground for ambience silencing", description = "Number of blocks above the ground that biome ambience will be silenced.\n" +
            "Set to zero to disable.")
    public static int cullSoundAboveGround = 32;

    @Config(name = "Biome sound blending", description = "Number of blocks to check for neighbouring biomes.\n" +
            "Set to zero to disable.")
    public static int biomeBlend = 32;

    @Config(name = "Beach ambience", description = "If true, plays ambient sounds in beach biomes.")
    public static boolean beach = true;

    @Config(name = "Badlands ambience", description = "If true, plays ambient sounds in badlands (mesa) biomes.")
    public static boolean badlands = true;

    @Config(name = "Cave biome ambience", description = "If true, plays ambience sounds in underground biomes such as dripstone and lush caves.")
    public static boolean caves = true;

    @Config(name = "Desert ambience", description = "If true, plays ambient sounds in desert biomes.")
    public static boolean desert = true;

    @Config(name = "Forest ambience", description = "If true, plays ambient sounds in forest biomes.")
    public static boolean forest = true;

    @Config(name = "Icy ambience", description = "If true, plays ambient sounds in cold (tundra, snowy) biomes.")
    public static boolean icy = true;

    @Config(name = "Jungle ambience", description = "If true, plays ambient sounds in jungle biomes.")
    public static boolean jungle = true;

    @Config(name = "Mountains ambience", description = "If true, plays ambient sounds in mountain biomes.")
    public static boolean mountains = true;

    @Config(name = "Ocean ambience", description = "If true, plays ambient sounds in ocean biomes when above the water surface.")
    public static boolean ocean = true;

    @Config(name = "Plains ambience", description = "If true, plays ambient sounds in plains biomes.")
    public static boolean plains = true;

    @Config(name = "River ambience", description = "If true, plays ambient sounds when in rivers and frozen rivers.")
    public static boolean river = true;

    @Config(name = "Savanna ambience", description = "If true, plays ambient sounds in savanna biomes.")
    public static boolean savanna = true;

    @Config(name = "Swamp ambience", description = "If true, plays ambient sounds in swamp biomes.")
    public static boolean swamp = true;

    @Config(name = "Taiga ambience", description = "If true, plays ambient sounds in taiga biomes.")
    public static boolean taiga = true;

    @Config(name = "The End ambience", description = "If true, plays ambient sounds in the End biomes.")
    public static boolean theEnd = true;

    @Config(name = "Valid biome dimensions", description = "Dimensions in which biome ambience will be played.")
    public static List<String> configDimensions = Arrays.asList(
        "minecraft:overworld",
        "minecraft:the_end"
    );

    @Override
    public void register() {
        Beach.register();
        Badlands.register();
        Caves.register();
        Desert.register();
        Forest.register();
        Icy.register();
        Jungle.register();
        Mountains.register();
        Ocean.register();
        Plains.register();
        River.register();
        Savanna.register();
        Swamp.register();
        Taiga.register();
        TheEnd.register();
    }

    @Override
    public void runWhenEnabled() {
        ClientEntityEvents.ENTITY_LOAD.register(this::handleEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(this::handleEntityUnload);
        ClientTickEvents.END_CLIENT_TICK.register(this::handleClientTick);

        configDimensions.forEach(dim -> VALID_DIMENSIONS.add(new ResourceLocation(dim)));
    }

    private void handleEntityLoad(Entity entity, Level level) {
        if (entity instanceof Player) {
            var result = AddBiomeAmbienceCallback.EVENT.invoker().interact(level);
            var id = level.dimension().location();

            if (result && !VALID_DIMENSIONS.contains(id)) {
                VALID_DIMENSIONS.add(id);
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

    public static class Handler extends SoundHandler<BiomeSound> {
        private Handler(@NotNull Player player) {
            super(player);

            if (beach) Beach.init(this);
            if (badlands) Badlands.init(this);
            if (caves) Caves.init(this);
            if (desert) Desert.init(this);
            if (forest) Forest.init(this);
            if (icy) Icy.init(this);
            if (jungle) Jungle.init(this);
            if (mountains) Mountains.init(this);
            if (ocean) Ocean.init(this);
            if (plains) Plains.init(this);
            if (river) River.init(this);
            if (savanna) Savanna.init(this);
            if (swamp) Swamp.init(this);
            if (taiga) Taiga.init(this);
            if (theEnd) TheEnd.init(this);
        }
    }
}
