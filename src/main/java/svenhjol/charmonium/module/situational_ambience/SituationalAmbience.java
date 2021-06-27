package svenhjol.charmonium.module.situational_ambience;

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
import svenhjol.charmonium.module.situational_ambience.sounds.*;

import java.util.ArrayList;
import java.util.List;

@Module(description = "Sounds that play according to an event or location.")
public class SituationalAmbience extends CharmoniumModule {
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
        if (player instanceof LocalPlayer && handler == null)
            handler = new Handler(player);
    }

    public static class Handler {
        private final Player player;
        private final List<SituationalSound> sounds = new ArrayList<>();

        public Handler(@NotNull Player player) {
            this.player = player;

            // TODO: config for these
            AlienSound.init(player, sounds);
            DeepslateSound.init(player, sounds);
            GeodeSound.init(player, sounds);
            GravelSound.init(player, sounds);
            HighSound.init(player, sounds);
            MineshaftSound.init(player, sounds);
            VillageSound.init(player, sounds);
        }

        public void tick() {
            if (!player.isAlive() || player.level == null)
                return;

            sounds.forEach(SituationalSound::tick);
        }
    }
}
