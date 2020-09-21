package svenhjol.charmonium.client.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import svenhjol.charm.module.PlayerState;
import svenhjol.charmonium.base.CharmoniumSounds;

import javax.annotation.Nullable;

public class VillageAmbientSounds extends BaseAmbientSounds {
    public VillageAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (world == null) return false;
        return PlayerState.client.village
            && PlayerState.client.isDaytime;
    }

    @Override
    public int getShortSoundDelay() {
        return world.random.nextInt(120) + 220;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_VILLAGE_SHORT;
    }

    @Override
    public float getShortSoundVolume() {
        return super.getShortSoundVolume() - 0.1F;
    }
}
