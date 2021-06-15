package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.helper.DimensionHelper;
import svenhjol.charmonium.init.CharmoniumSounds;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public class EndAmbientSounds extends BaseAmbientSounds {
    public EndAmbientSounds(Player player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    public boolean isValid() {
        if (world == null) return false;
        return DimensionHelper.isEnd(world);
    }

    @Override
    public int getShortSoundDelay() {
        return world.random.nextInt(300) + 300;
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_END_LONG;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_END_SHORT;
    }

    @Override
    public float getShortSoundVolume() {
        return 0.45F;
    }

    @Override
    public float getLongSoundVolume() {
        return 0.35F;
    }
}
