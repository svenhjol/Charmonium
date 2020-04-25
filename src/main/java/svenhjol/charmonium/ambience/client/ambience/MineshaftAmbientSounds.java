package svenhjol.charmonium.ambience.client.ambience;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import svenhjol.charm.Charm;
import svenhjol.strange.Strange;
import svenhjol.charmonium.ambience.client.ambience.BaseAmbientSounds;
import svenhjol.charmonium.base.CharmoniumSounds;

import javax.annotation.Nullable;

public class MineshaftAmbientSounds extends BaseAmbientSounds {
    public MineshaftAmbientSounds(PlayerEntity player, SoundHandler soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (world == null) return false;
        return Charm.client.isInMineshaft;
    }

    @Override
    public int getShortSoundDelay() {
        return world.rand.nextInt(80) + 160;
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return null;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_MINESHAFT_SHORT;
    }
}
