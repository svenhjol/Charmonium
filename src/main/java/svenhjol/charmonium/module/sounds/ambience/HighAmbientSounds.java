package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.helper.DimensionHelper;
import svenhjol.charmonium.init.CharmoniumSounds;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public class HighAmbientSounds extends BaseAmbientSounds {
    public HighAmbientSounds(Player player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        return DimensionHelper.isDimension(world, new ResourceLocation("overworld"))
            && player.blockPosition().getY() > 150
            && !player.isUnderWater();
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_HIGH;
    }
}
