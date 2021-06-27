package svenhjol.charmonium.module.sounds.ambience;

import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.AmethystBlock;
import org.jetbrains.annotations.Nullable;
import svenhjol.charm.helper.PosHelper;
import svenhjol.charmonium.init.CharmoniumSounds;

import java.util.Optional;

public class AmethystAmbientSounds extends BaseAmbientSounds {
    public static int validCheckTicks = 0;
    public static double distanceFromAmethyst = -1;

    public AmethystAmbientSounds(Player player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public boolean isValid() {
        if (validCheckTicks++ < 60)
            return distanceFromAmethyst >= 0;

        validCheckTicks = 0;
        distanceFromAmethyst = -1;

        if (world == null) return false;
        if (isOutside()) return false;

        Optional<BlockPos> opt = BlockPos.findClosestMatch(player.blockPosition(), 32, 16, pos
            -> world.getBlockState(pos).getBlock() instanceof AmethystBlock);

        if (opt.isPresent()) {
            BlockPos foundPos = opt.get();
            distanceFromAmethyst = PosHelper.getDistanceSquared(player.blockPosition(), foundPos);
            return true;
        }

        return false;
    }

    @Override
    public int getShortSoundDelay() {
        return world.random.nextInt(400) + 300;
    }

    @Override
    public float getShortSoundVolume() {
        if (distanceFromAmethyst == -1)
            return 0.0F;

        double vol = 0.18F * Math.max(0, 300 - distanceFromAmethyst) / 300;
        return (float)vol;
    }

    @Override
    public float getShortSoundPitch() {
        if (distanceFromAmethyst == -1)
            return 0.0F;

        double pitch = 0.8F + (1.5F * getShortSoundVolume());
        return (float)pitch;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_AMETHYST_SHORT;
    }
}
