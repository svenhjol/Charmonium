package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public class VillageAmbientSounds extends BaseAmbientSounds {
    public VillageAmbientSounds(Player player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (world == null) return false;
        if (!world.canSeeSkyFromBelowWater(player.blockPosition())) return false;

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
