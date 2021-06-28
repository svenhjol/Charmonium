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
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.CharmoniumModule;
import svenhjol.charmonium.module.biome_ambience.BiomeSounds.*;

@Module(description = "Ambient background sound plays according the biome and time of day.")
public class BiomeAmbience extends CharmoniumModule {
    public Handler handler;

    @Override
    public void init() {
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
        if (handler != null)
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

            // TODO: config for each biome type
            Beach.init(this);
            Badlands.init(this);
            Desert.init(this);
            Forest.init(this);
            Icy.init(this);
            Jungle.init(this);
            Mountains.init(this);
            Ocean.init(this);
            Plains.init(this);
            Savanna.init(this);
            Swamp.init(this);
            Taiga.init(this);
            TheEnd.init(this);
        }
    }
}
