package svenhjol.charmonium.module.biome_ambience;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.ClientModule;
import svenhjol.charmonium.annotation.Config;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.loader.CharmModule;
import svenhjol.charmonium.module.biome_ambience.BiomeSounds.*;

@ClientModule(mod = Charmonium.MOD_ID, description = "Plays ambient background sound according to the biome and time of day.")
public class BiomeAmbience extends CharmModule {
    public Handler handler;

    @Config(name = "Volume scaling", description = "Affects the volume of all biome ambient sounds. 1.0 is full volume.")
    public static float volumeScaling = 0.55F;

    @Config(name = "Beach ambience", description = "If true, plays ambient sounds in beach biomes.")
    public static boolean beach = true;

    @Config(name = "Badlands ambience", description = "If true, plays ambient sounds in badlands (mesa) biomes.")
    public static boolean badlands = true;

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

    @Config(name = "Savanna ambience", description = "If true, plays ambient sounds in savanna biomes.")
    public static boolean savanna = true;

    @Config(name = "Swamp ambience", description = "If true, plays ambient sounds in swamp biomes.")
    public static boolean swamp = true;

    @Config(name = "Taiga ambience", description = "If true, plays ambient sounds in taiga biomes.")
    public static boolean taiga = true;

    @Config(name = "The End ambience", description = "If true, plays ambient sounds in the End biomes.")
    public static boolean theEnd = true;

    @Override
    public void runWhenEnabled() {
        ClientEntityEvents.ENTITY_LOAD.register(this::handleEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(this::handleEntityUnload);
        ClientTickEvents.END_CLIENT_TICK.register(this::handleClientTick);
    }

    private void handleEntityLoad(Entity entity, Level level) {
        if (entity instanceof Player)
            trySetupSoundHandler((Player)entity);
    }

    private void handleEntityUnload(Entity entity, Level level) {
        if (entity instanceof LocalPlayer && handler != null)
            handler.stop();
    }

    private void handleClientTick(Minecraft client) {
        if (handler != null && !client.isPaused())
            handler.tick();
    }

    public void trySetupSoundHandler(Player player) {
        if (!(player instanceof LocalPlayer)) return;

        if (handler == null)
            handler = new Handler(player);

        handler.updatePlayer(player);
    }

    public static class Handler extends SoundHandler<BiomeSound> {
        private Handler(@NotNull Player player) {
            super(player);

            if (beach) Beach.init(this);
            if (badlands) Badlands.init(this);
            if (desert) Desert.init(this);
            if (forest) Forest.init(this);
            if (icy) Icy.init(this);
            if (jungle) Jungle.init(this);
            if (mountains) Mountains.init(this);
            if (ocean) Ocean.init(this);
            if (plains) Plains.init(this);
            if (savanna) Savanna.init(this);
            if (swamp) Swamp.init(this);
            if (taiga) Taiga.init(this);
            if (theEnd) TheEnd.init(this);
        }
    }
}
