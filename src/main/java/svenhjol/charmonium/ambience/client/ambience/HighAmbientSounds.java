package svenhjol.charmonium.ambience.client.ambience;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.dimension.DimensionType;
import svenhjol.charmonium.ambience.client.ambience.BaseAmbientSounds;
import svenhjol.charmonium.base.CharmoniumSounds;

import javax.annotation.Nullable;

public class HighAmbientSounds extends BaseAmbientSounds {
    public HighAmbientSounds(PlayerEntity player, SoundHandler soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        return world.dimension.getType() == DimensionType.OVERWORLD
            && player.getPosition().getY() > 150
            && !player.canSwim();
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_HIGH;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return null;
    }
}
