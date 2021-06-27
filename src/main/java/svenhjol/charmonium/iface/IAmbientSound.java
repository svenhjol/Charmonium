package svenhjol.charmonium.iface;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public interface IAmbientSound {
    ClientLevel getLevel();

    Player getPlayer();

    SoundManager getSoundManager();

    boolean isValid();

    void tick();

    @Nullable
    default SoundEvent getSound() { return null; }

    default int getDelay() { return 0; }

    default float getVolume() { return 1.0F; }

    default float getPitch() { return 1.0F; }
}
