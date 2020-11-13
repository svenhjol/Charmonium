package svenhjol.charmonium.client.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import svenhjol.charm.client.PlayerStateClient;
import svenhjol.charmonium.base.CharmoniumSounds;

import javax.annotation.Nullable;

public class MineshaftAmbientSounds extends BaseAmbientSounds {
    public MineshaftAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (world == null) return false;
        return PlayerStateClient.INSTANCE.mineshaft;
    }

    @Override
    public int getShortSoundDelay() {
        return world.random.nextInt(500) + 320;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_MINESHAFT_SHORT;
    }
}
