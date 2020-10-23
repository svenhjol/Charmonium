package svenhjol.charmonium.client.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.charm.base.helper.DimensionHelper;

import javax.annotation.Nullable;

public class HighAmbientSounds extends BaseAmbientSounds {
    public HighAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        return DimensionHelper.isDimension(world, new Identifier("overworld"))
            && player.getBlockPos().getY() > 150
            && !player.isSubmergedInWater();
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_HIGH;
    }
}
