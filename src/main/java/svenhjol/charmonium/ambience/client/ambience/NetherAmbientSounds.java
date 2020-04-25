package svenhjol.charmonium.ambience.client.ambience;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.dimension.DimensionType;
import svenhjol.charmonium.ambience.client.ambience.BaseAmbientSounds;
import svenhjol.charmonium.base.CharmoniumSounds;

import javax.annotation.Nullable;

public class NetherAmbientSounds extends BaseAmbientSounds {
    public NetherAmbientSounds(PlayerEntity player, SoundHandler soundHandler) {
        super(player, soundHandler);
    }

    public boolean isValid() {
        if (world == null) return false;
        return world.getDimension().getType() == DimensionType.THE_NETHER;
    }

    @Override
    public int getShortSoundDelay() {
        return world.rand.nextInt(200) + 200;
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_NETHER_LONG;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_NETHER_SHORT;
    }
}
