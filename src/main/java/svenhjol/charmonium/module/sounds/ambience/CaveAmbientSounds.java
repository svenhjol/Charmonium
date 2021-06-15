package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.helper.DimensionHelper;
import svenhjol.charmonium.init.CharmoniumSounds;

import javax.annotation.Nullable;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public class CaveAmbientSounds extends BaseAmbientSounds {
    public CaveAmbientSounds(Player player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    public static boolean isValidCave(ClientLevel world, Player player) {
        if (world == null || !DimensionHelper.isDimension(world, new ResourceLocation("overworld"))) return false;
        if (player.isUnderWater()) return false;

        BlockPos pos = player.blockPosition();
        int light = world.getMaxLocalRawBrightness(pos);

        if (!world.canSeeSkyFromBelowWater(pos)
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
