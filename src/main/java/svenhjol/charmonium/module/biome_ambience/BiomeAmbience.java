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
import svenhjol.charmonium.init.CharmoniumBiomes;
import svenhjol.charmonium.loader.CharmModule;
import svenhjol.charmonium.module.biome_ambience.BiomeSounds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ClientModule(mod = Charmonium.MOD_ID, description = "Plays ambient background sound according to the biome and time of day.")
public class BiomeAmbience extends CharmModule {
    public Handler handler;

    public static List<ResourceLocation> VALID_DIMENSIONS = new ArrayList<>();

    @Config(name = "Volume scaling", description = "Affects the volume of all biome ambient sounds. 1.0 is full volume.")
    public static float volumeScaling = 0.55F;

    @Config(name = "Above Ground For Ambience Silencing", description = "Number of blocks above the ground that biome ambience will be silenced.")
    public static int cullSoundAboveGround = 24;

    @Config(name = "Valid dimensions", description = "Dimensions in which biome ambience will be played.")
    public static List<String> configDimensions = Arrays.asList(
        "minecraft:overworld",
        "minecraft:the_end"
    );

    @Config(name = "Extra badlands biomes", description = "Biomes that will have badlands ambient sounds.")
    public static List<String> extraBadlandsBiomes = new ArrayList<>();

    @Config(name = "Extra beach biomes", description = "Biomes that will have beach ambient sounds.")
    public static List<String> extraBeachBiomes = new ArrayList<>();

    @Config(name = "Extra desert biomes", description = "Biomes that will have desert ambient sounds.")
    public static List<String> extraDesertBiomes = new ArrayList<>();

    @Config(name = "Extra forest biomes", description = "Biomes that will have forest ambient sounds.")
    public static List<String> extraForestBiomes = new ArrayList<>();

    @Config(name = "Extra icy biomes", description = "Biomes that will have icy ambient sounds.")
    public static List<String> extraIcyBiomes = new ArrayList<>();

    @Config(name = "Extra jungle biomes", description = "Biomes that will have jungle ambient sounds.")
    public static List<String> extraJungleBiomes = new ArrayList<>();

    @Config(name = "Extra mountain biomes", description = "Biomes that will have mountain ambient sounds.")
    public static List<String> extraMountainBiomes = new ArrayList<>();

    @Config(name = "Extra ocean biomes", description = "Biomes that will have ocean ambient sounds.")
    public static List<String> extraOceanBiomes = new ArrayList<>();

    @Config(name = "Extra plains biomes", description = "Biomes that will have plains ambient sounds.")
    public static List<String> extraPlainsBiomes = new ArrayList<>();

    @Config(name = "Extra river biomes", description = "Biomes that will have river ambient sounds.")
    public static List<String> extraRiverBiomes = new ArrayList<>();

    @Config(name = "Extra savanna biomes", description = "Biomes that will have savanna ambient sounds.")
    public static List<String> extraSavannaBiomes = new ArrayList<>();

    @Config(name = "Extra swamp biomes", description = "Biomes that will have swamp ambient sounds.")
    public static List<String> extraSwampBiomes = new ArrayList<>();

    @Config(name = "Extra taiga biomes", description = "Biomes that will have taiga ambient sounds.")
    public static List<String> extraTaigaBiomes = new ArrayList<>();

    @Config(name = "Extra End biomes", description = "Biomes that will have The End ambient sounds.")
    public static List<String> extraEndBiomes = new ArrayList<>();

    @Override
    public void register() {
        registerSounds();
    }

    @Override
    public void runWhenEnabled() {
        ClientEntityEvents.ENTITY_LOAD.register(this::handleEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(this::handleEntityUnload);
        ClientTickEvents.END_CLIENT_TICK.register(this::handleClientTick);

        configDimensions.forEach(dim -> VALID_DIMENSIONS.add(new ResourceLocation(dim)));

        extraBadlandsBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.BADLANDS));
        extraBeachBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.BEACH));
        extraDesertBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.DESERT));
        extraForestBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.FOREST));
        extraIcyBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.ICY));
        extraJungleBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.JUNGLE));
        extraMountainBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.MOUNTAIN));
        extraOceanBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.OCEAN));
        extraPlainsBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.PLAINS));
        extraRiverBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.RIVER));
        extraSavannaBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.SAVANNA));
        extraSwampBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.SWAMP));
        extraTaigaBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.TAIGA));
        extraEndBiomes.forEach(r -> CharmoniumBiomes.tryAddBiome(r, CharmoniumBiomes.THEEND));
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

    public void registerSounds() {
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
