package svenhjol.charmonium.module.biome_ambience;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.annotation.Module;
import svenhjol.charmonium.module.CharmoniumModule;
import svenhjol.charmonium.module.biome_ambience.BiomeSounds.*;

import java.util.ArrayList;
import java.util.List;

@Module(description = "Ambient background sound plays according the biome and time of day.")
public class BiomeAmbience extends CharmoniumModule {
    public Handler handler;

    @Override
    public void init() {
        ClientEntityEvents.ENTITY_LOAD.register(this::handleEntityLoad);
        ClientTickEvents.END_CLIENT_TICK.register(this::handleClientTick);
    }

    private void handleEntityLoad(Entity entity, Level level) {
        if (entity instanceof Player)
            trySetupSoundHandler((Player)entity);
    }

    private void handleClientTick(Minecraft client) {
        if (client.player != null && client.level != null && handler != null)
            handler.tick();
    }

    public void trySetupSoundHandler(Player player) {
        if (player instanceof LocalPlayer)
            handler = new Handler(player);
    }

    public static class Handler {
        private final Player player;
        private final List<BiomeSound> sounds = new ArrayList<>();

        public Handler(@NotNull Player player) {
            this.player = player;

            // TODO: config for each biome type
            Beach.init(player, sounds);
            Badlands.init(player, sounds);
            Desert.init(player, sounds);
            Forest.init(player, sounds);
            Icy.init(player, sounds);
            Jungle.init(player, sounds);
            Mountains.init(player, sounds);
            Ocean.init(player, sounds);
            Plains.init(player, sounds);
            Savanna.init(player, sounds);
            Swamp.init(player, sounds);
            Taiga.init(player, sounds);
        }

        public void tick() {
            if (!player.isAlive() || player.level == null)
                return;

            sounds.forEach(BiomeSound::tick);
        }
    }
}
