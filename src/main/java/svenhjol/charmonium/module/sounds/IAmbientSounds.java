package svenhjol.charmonium.module.sounds;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;

import javax.annotation.Nullable;

public interface IAmbientSounds {
    ClientWorld getWorld();

    PlayerEntity getPlayer();

    SoundManager getSoundManager();

    boolean isValid();

    @Nullable
    default SoundEvent getLongSound() {
        return null;
    }

    @Nullable
    default SoundEvent getShortSound() {
        return null;
    }
}
