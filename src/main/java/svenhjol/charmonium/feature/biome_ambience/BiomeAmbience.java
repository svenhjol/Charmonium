package svenhjol.charmonium.feature.biome_ambience;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.feature.biome_ambience.sounds.*;
import svenhjol.charmonium.sound.BiomeSound;
import svenhjol.charmonium.sound.ISoundType;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmony.annotation.Configurable;
import svenhjol.charmony.client.ClientFeature;
import svenhjol.charmony_api.event.ClientEntityJoinEvent;
import svenhjol.charmony_api.event.ClientEntityLeaveEvent;
import svenhjol.charmony_api.event.ClientTickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BiomeAmbience extends ClientFeature {
    public static List<ResourceLocation> VALID_DIMENSIONS = new ArrayList<>();
    private static final ISoundType<BiomeSound> BADLANDS = new Badlands();
    private static final ISoundType<BiomeSound> BEACH = new Beach();
    private static final ISoundType<BiomeSound> CAVES = new Caves();
    private static final ISoundType<BiomeSound> DESERT = new Desert();
    private static final ISoundType<BiomeSound> FOREST = new Forest();
    private static final ISoundType<BiomeSound> ICY = new Icy();
    private static final ISoundType<BiomeSound> JUNGLE = new Jungle();
    private static final ISoundType<BiomeSound> MOUNTAINS = new Mountains();
    private static final ISoundType<BiomeSound> OCEAN = new Ocean();
    private static final ISoundType<BiomeSound> PLAINS = new Plains();
    private static final ISoundType<BiomeSound> RIVER = new River();
    private static final ISoundType<BiomeSound> SAVANNA = new Savanna();
    private static final ISoundType<BiomeSound> SWAMP = new Swamp();
    private static final ISoundType<BiomeSound> TAIGA = new Taiga();
    private static final ISoundType<BiomeSound> THE_END = new TheEnd();
    private Handler handler;

    @Configurable(name = "Above ground for ambience silencing", description = "Number of blocks above the ground that biome ambience will be silenced.\n" +
        "Set to zero to disable.", requireRestart = false)
    public static int cullSoundAboveGround = 32;

    @Configurable(name = "Biome sound blending", description = "Number of blocks to check for neighbouring biomes.\n" +
        "Set to zero to disable.", requireRestart = false)
    public static int biomeBlend = 32;

    @Configurable(name = "Volume scaling", description = "Affects the volume of all biome ambient sounds. 1.0 is full volume.", requireRestart = false)
    public static double volumeScaling = 0.55D;

    @Configurable(name = "Valid dimensions", description = "Dimensions in which biome ambience will be played.")
    public static List<String> dimensions = Arrays.asList(
        "minecraft:overworld",
        "minecraft:the_end"
    );

    @Override
    public String description() {
        return "Plays ambient background sound according to the biome and time of day.";
    }

    @Override
    public void runWhenEnabled() {
        ClientEntityJoinEvent.INSTANCE.handle(this::handleClientEntityJoin);
        ClientEntityLeaveEvent.INSTANCE.handle(this::handleClientEntityLeave);
        ClientTickEvent.INSTANCE.handle(this::handleClientTick);

        dimensions.forEach(dim -> VALID_DIMENSIONS.add(new ResourceLocation(dim)));
    }

    private void handleClientTick(Minecraft client) {
        if (handler != null && !client.isPaused()) {
            handler.tick();
        }
    }

    private void handleClientEntityLeave(Entity entity, Level level) {
        if (entity instanceof LocalPlayer && handler != null) {
            handler.stop();
        }
    }

    private void handleClientEntityJoin(Entity entity, Level level) {
        if (entity instanceof LocalPlayer player) {
            trySetupSoundHandler(player);
        }
    }

    private void trySetupSoundHandler(Player player) {
        if (!(player instanceof LocalPlayer)) return;

        if (handler == null) {
            handler = new Handler(player);
        }

        handler.updatePlayer(player);
    }

    public static class Handler extends SoundHandler<BiomeSound> {
        public Handler(@NotNull Player player) {
            super(player);

            BADLANDS.addSounds(this);
            BEACH.addSounds(this);
            CAVES.addSounds(this);
            DESERT.addSounds(this);
            FOREST.addSounds(this);
            ICY.addSounds(this);
            JUNGLE.addSounds(this);
            MOUNTAINS.addSounds(this);
            OCEAN.addSounds(this);
            PLAINS.addSounds(this);
            RIVER.addSounds(this);
            SAVANNA.addSounds(this);
            SWAMP.addSounds(this);
            TAIGA.addSounds(this);
            THE_END.addSounds(this);
        }
    }
}
