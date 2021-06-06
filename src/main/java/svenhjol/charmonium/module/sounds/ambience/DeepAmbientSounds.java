package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.helper.DimensionHelper;
import svenhjol.charmonium.init.CharmoniumSounds;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public class DeepAmbientSounds extends BaseAmbientSounds {
    public DeepAmbientSounds(Player player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (world == null || !DimensionHelper.isDimension(world, new ResourceLocation("overworld"))) return false;
        BlockPos pos = player.blockPosition();
        int light = world.getMaxLocalRawBrightness(pos);
        int bottom = world.getMinBuildHeight() < 0 ? 0 : 32;
        return !world.canSeeSkyFromBelowWater(pos) && pos.getY() <= bottom && light < 10;
    }

    @Override
    public int getShortSoundDelay() {
        return world.random.nextInt(400) + 1000;
    }

    @Override
    public float getLongSoundVolume() {
        return 0.65F;
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_DEEP_LONG;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_DEEP_SHORT;
    }
}
