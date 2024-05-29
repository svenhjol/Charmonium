package svenhjol.charmonium.feature.world_ambience.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.charmony.feature.FeatureHolder;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmonium.sound.SoundHandler;

public final class Handlers extends FeatureHolder<WorldAmbience> {
    private Handler handler;

    public Handlers(WorldAmbience feature) {
        super(feature);
    }

    public void handleClientTick(Minecraft client) {
        if (handler != null && !client.isPaused()) {
            handler.tick();
        }
    }

    public void handleClientEntityLeave(Entity entity, Level level) {
        if (entity instanceof LocalPlayer && handler != null) {
            handler.stop();
        }
    }

    public void handleClientEntityJoin(Entity entity, Level level) {
        if (entity instanceof LocalPlayer player) {
            trySetupSoundHandler(player);
        }
    }

    public void trySetupSoundHandler(Player player) {
        if (!(player instanceof LocalPlayer)) return;

        handler = new Handler(player);
        handler.updatePlayer(player);
    }

    public class Handler extends SoundHandler<WorldSound> {
        public Handler(@NotNull Player player) {
            super(player);
            var registers = feature().registers;

            registers.ALIEN.addSounds(this);
            registers.BLEAK.addSounds(this);
            registers.CAVE_DRONE.addSounds(this);
            registers.CAVE_DEPTH.addSounds(this);
            registers.DEEPSLATE.addSounds(this);
            registers.DRY.addSounds(this);
            registers.GEODE.addSounds(this);
            registers.GRAVEL.addSounds(this);
            registers.HIGH.addSounds(this);
            registers.MANSION.addSounds(this);
            registers.MINESHAFT.addSounds(this);
            registers.NIGHT_PLAINS.addSounds(this);
            registers.SNOWSTORM.addSounds(this);
            registers.UNDERGROUND_WATER.addSounds(this);
            registers.VILLAGE.addSounds(this);
        }
    }
}
