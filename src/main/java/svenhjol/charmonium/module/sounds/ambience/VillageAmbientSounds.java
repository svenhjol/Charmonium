package svenhjol.charmonium.module.sounds.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;

import javax.annotation.Nullable;

public class VillageAmbientSounds extends BaseAmbientSounds {
    public VillageAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (world == null) return false;
        if (!world.isSkyVisibleAllowingSea(player.getBlockPos())) return false;

        return PlayerStateClient.INSTANCE.village
            && PlayerStateClient.INSTANCE.isDaytime;
    }

    @Override
    public int getShortSoundDelay() {
        return world.random.nextInt(500) + 320;
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
