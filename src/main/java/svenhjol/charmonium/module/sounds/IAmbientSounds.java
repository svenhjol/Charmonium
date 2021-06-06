package svenhjol.charmonium.module.sounds;

import javax.annotation.Nullable;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public interface IAmbientSounds {
    ClientLevel getWorld();

    Player getPlayer();

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
