package svenhjol.charmonium.feature.biome_ambience.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.charmony.feature.FeatureHolder;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;
import svenhjol.charmonium.sound.SoundHandler;

public final class Handlers extends FeatureHolder<BiomeAmbience> {
    private Handler handler;

    public Handlers(BiomeAmbience feature) {
        super(feature);
    }

    public void clientTick(Minecraft client) {
        if (handler != null && !client.isPaused()) {
            handler.tick();
        }
    }

    public void clientEntityLeave(Entity entity, Level level) {
        if (entity instanceof LocalPlayer && handler != null) {
            handler.stop();
        }
    }

    public void clientEntityJoin(Entity entity, Level level) {
        if (entity instanceof LocalPlayer player) {
            trySetupSoundHandler(player);
        }
    }

    public void trySetupSoundHandler(Player player) {
        if (!(player instanceof LocalPlayer)) return;

        handler = new Handler(player);
        handler.updatePlayer(player);
    }

    public class Handler extends SoundHandler<BiomeSound> {
        public Handler(@NotNull Player player) {
            super(player);
            var registers = Handlers.this.feature().registers;

            registers.BADLANDS.addSounds(this);
            registers.BEACH.addSounds(this);
            registers.CAVES.addSounds(this);
            registers.DESERT.addSounds(this);
            registers.FOREST.addSounds(this);
            registers.ICY.addSounds(this);
            registers.JUNGLE.addSounds(this);
            registers.MOUNTAINS.addSounds(this);
            registers.OCEAN.addSounds(this);
            registers.PLAINS.addSounds(this);
            registers.RIVER.addSounds(this);
            registers.SAVANNA.addSounds(this);
            registers.SWAMP.addSounds(this);
            registers.TAIGA.addSounds(this);
            registers.THE_END.addSounds(this);
        }
    }
}
