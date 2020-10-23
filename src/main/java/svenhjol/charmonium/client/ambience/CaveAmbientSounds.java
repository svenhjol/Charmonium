package svenhjol.charmonium.client.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import svenhjol.charm.base.helper.DimensionHelper;
import svenhjol.charmonium.base.CharmoniumSounds;


import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaveAmbientSounds extends BaseAmbientSounds {
    public CaveAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    public static boolean isValidCave(ClientWorld world, PlayerEntity player) {
        if (world == null || !DimensionHelper.isDimension(world, new Identifier("overworld"))) return false;
        if (player.isSubmergedInWater()) return false;

        BlockPos pos = player.getBlockPos();
        int light = world.getLightLevel(pos);

        if (!world.isSkyVisibleAllowingSea(pos)
            && pos.getY() <= world.getSeaLevel()
        ) {
            return pos.getY() <= 44 || light <= 10;
        }

        return false;
    }

    @Override
    public boolean isValid() {
        return isValidCave(world, player);
    }

    @Override
    public int getShortSoundDelay() {
        return world.random.nextInt(500) + 600;
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
}
