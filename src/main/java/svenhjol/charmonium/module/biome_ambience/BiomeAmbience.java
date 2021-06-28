package svenhjol.charmonium.module.biome_ambience;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import svenhjol.charmonium.annotation.Module;
import svenhjol.charmonium.module.CharmoniumModule;

import java.util.ArrayList;
import java.util.List;

@Module(description = "Ambient background sound plays according the biome and time of day.")
public class BiomeAmbience extends CharmoniumModule {
    private Player player;
    private boolean hasInitSounds;
    private final List<BiomeSound> sounds = new ArrayList<>();

    @Override
    public void init() {
        ClientEntityEvents.ENTITY_LOAD.register(this::handleEntityLoad);
        ClientTickEvents.END_CLIENT_TICK.register(this::handleClientTick);
    }

    private void handleEntityLoad(Entity entity, Level level) {
        if (entity instanceof Player)
            this.player = (Player)entity;

        if (!hasInitSounds)
            initSounds();
    }

    private void handleClientTick(Minecraft client) {
        if (client.player != null && client.level != null)
            tick();
    }

    private void initSounds() {
        BiomeSounds.Beach.init(player, sounds);
        BiomeSounds.Badlands.init(player, sounds);
        BiomeSounds.Desert.init(player, sounds);
        BiomeSounds.Forest.init(player, sounds);
        BiomeSounds.Icy.init(player, sounds);
        BiomeSounds.Jungle.init(player, sounds);
        BiomeSounds.Mountains.init(player, sounds);
        BiomeSounds.Ocean.init(player, sounds);
        BiomeSounds.Plains.init(player, sounds);
        BiomeSounds.Savanna.init(player, sounds);
        BiomeSounds.Swamp.init(player, sounds);
        BiomeSounds.Taiga.init(player, sounds);
        BiomeSounds.TheEnd.init(player, sounds);

        hasInitSounds = true;
    }

    private void tick() {
        if (!player.isAlive() || player.level == null)
            return;

        sounds.forEach(BiomeSound::tick);
    }
}
