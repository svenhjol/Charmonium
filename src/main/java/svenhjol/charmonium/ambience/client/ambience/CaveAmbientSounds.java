package svenhjol.charmonium.ambience.client.ambience;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import svenhjol.charm.Charm;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.meson.helper.WorldHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaveAmbientSounds extends BaseAmbientSounds {
    public CaveAmbientSounds(PlayerEntity player, SoundHandler soundHandler) {
        super(player, soundHandler);
    }

    public static boolean isValidCave(ClientWorld world, PlayerEntity player) {
        if (world == null || world.getDimension().getType() != DimensionType.OVERWORLD) return false;
        if (player.canSwim()) return false;

        BlockPos pos = player.getPosition();
        int light = world.getLight(pos);

        if (!WorldHelper.canSeeSky(world, pos)
            && pos.getY() <= world.getSeaLevel()
        ) {
            return pos.getY() <= 44 || light <= 12;
        }

        return false;
    }

    @Override
    public boolean isValid() {
        return isValidCave(world, player);
    }

    @Override
    public int getShortSoundDelay() {
        return world.rand.nextInt(300) + 600;
    }

    @Override
    public float getLongSoundVolume() {
        return 0.35F;
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_CAVE_LONG;
    }

    @Nullable
    @Override
    public SoundEvent getShortSound() {
        return CharmoniumSounds.AMBIENCE_CAVE_SHORT;
    }

    public static class CrystalCaves extends BaseAmbientSounds {
        protected int delay = 0;
        protected boolean eagerCheck = true;

        public CrystalCaves(PlayerEntity player, SoundHandler soundHandler) {
            super(player, soundHandler);
        }

        @Override
        public int getShortSoundDelay() {
            if (eagerCheck) {
                Charmonium.LOG.debug("[CrystalCaves] eagerly checking");
                delay = world.rand.nextInt(100) + 120;
            } else {
                delay = 400;
            }
            return delay;
        }

        @Override
        public boolean isValid() {
            return isValidCave(world, player);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return null;
        }

        @Override
        protected void setShortSound() {
            BlockPos pos = player.getPosition();

            int range = 16;
            Stream<BlockPos> inRange = BlockPos.getAllInBox(pos.add(-range, -range, -range), pos.add(range, range, range));
            List<BlockPos> blocks = inRange.map(BlockPos::toImmutable).collect(Collectors.toList());

            for (BlockPos pp : blocks) {
                if (Charm.quarkCompat != null
                    && Charm.quarkCompat.isCrystal(world, pp)
                ) {
                    super.setShortSound();
                    this.eagerCheck = true;
                    return;
                }
            }

            this.eagerCheck = false;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_CRYSTALS_SHORT;
        }
    }
}
