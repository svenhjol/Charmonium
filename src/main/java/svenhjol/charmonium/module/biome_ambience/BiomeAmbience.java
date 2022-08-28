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

    @Config(name = "Valid dimensions", description = "Dimensions in which biome ambience will be played.")
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
        if (entity instanceof Player player) {
            var result = AddBiomeAmbienceCallback.EVENT.invoker().interact(level);
            var id = level.dimension().location();

            if (result && !VALID_DIMENSIONS.contains(id)) {
                VALID_DIMENSIONS.add(id);
            }

            trySetupSoundHandler(player);
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

            Beach.init(this);
            Badlands.init(this);
            Caves.init(this);
            Desert.init(this);
            Forest.init(this);
            Icy.init(this);
            Jungle.init(this);
            Mountains.init(this);
            Ocean.init(this);
            Plains.init(this);
            River.init(this);
            Savanna.init(this);
            Swamp.init(this);
            Taiga.init(this);
            TheEnd.init(this);
        }
    }
}
