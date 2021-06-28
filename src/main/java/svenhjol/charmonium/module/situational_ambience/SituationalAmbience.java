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
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.CharmoniumModule;
import svenhjol.charmonium.module.situational_ambience.sounds.*;

@Module(description = "Sounds that play according to an event or location.")
public class SituationalAmbience extends CharmoniumModule {
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
        if (handler != null && !client.isPaused())
            handler.tick();
    }

    public void trySetupSoundHandler(Player player) {
        if (!(player instanceof LocalPlayer)) return;

        if (handler == null)
            handler = new Handler(player);

        handler.updatePlayer(player);
    }

    public static class Handler extends SoundHandler<SituationalSound> {
        public Handler(@NotNull Player player) {
            super(player);

            // TODO: config for these
            AlienSound.init(this);
            BleakSound.init(this);
            CaveWaterSound.init(this);
            DeepslateSound.init(this);
            DrySound.init(this);
            GeodeSound.init(this);
            GravelSound.init(this);
            HighSound.init(this);
            MansionSound.init(this);
            MineshaftSound.init(this);
            NightPlainsSound.init(this);
            SnowstormSound.init(this);
            VillageSound.init(this);
        }
    }
}
