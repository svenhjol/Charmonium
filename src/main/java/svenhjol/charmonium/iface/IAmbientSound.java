package svenhjol.charmonium.iface;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public interface IAmbientSound {
    ClientLevel getLevel();

    Player getPlayer();

    AbstractTickableSoundInstance getSoundInstance();

    boolean isValid();

    void tick();

    void updatePlayer(Player player);

    default SoundManager getSoundManager() {
        return Minecraft.getInstance().getSoundManager();
    }

    default boolean isPlaying() {
        return getSoundInstance() != null && getSoundManager().isActive(getSoundInstance());
    }

    default void stop() {
        getSoundManager().stop(getSoundInstance());
    }

    default int getDelay() { return 0; }

    default float getVolume() { return 1.0F; }

    default float getVolumeScaling() { return 0.55F; }

    default float getPitch() { return 1.0F; }

    @Nullable
    default SoundEvent getSound() { return null; }
}
