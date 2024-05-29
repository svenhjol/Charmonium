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

            registers.alien.addSounds(this);
            registers.bleak.addSounds(this);
            registers.caveDrone.addSounds(this);
            registers.caveDepth.addSounds(this);
            registers.deepslate.addSounds(this);
            registers.dry.addSounds(this);
            registers.geode.addSounds(this);
            registers.gravel.addSounds(this);
            registers.high.addSounds(this);
            registers.mansion.addSounds(this);
            registers.mineshaft.addSounds(this);
            registers.nightPlains.addSounds(this);
            registers.snowstorm.addSounds(this);
            registers.undergroundWater.addSounds(this);
            registers.village.addSounds(this);
        }
    }
}
