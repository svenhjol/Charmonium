package svenhjol.charmonium.client.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.meson.helper.DimensionHelper;

import javax.annotation.Nullable;

public class DeepAmbientSounds extends BaseAmbientSounds {
    public DeepAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (world == null || !DimensionHelper.isDimension(world, new Identifier("overworld"))) return false;
        BlockPos pos = player.getBlockPos();
        int light = world.getLightLevel(pos);
        return !world.isSkyVisibleAllowingSea(pos) && pos.getY() <= 32 && light < 10;
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
