package svenhjol.charmonium.module.biome_ambience;

import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.helper.WorldHelper;

public abstract class SurfaceBiomeSound extends BiomeSound {
    protected boolean playWhenThundering;

    protected SurfaceBiomeSound(Player player, boolean playWhenThundering) {
        super(player);
        this.playWhenThundering = playWhenThundering;
    }

    @Override
    public boolean isValidPlayerCondition() {
        if (WorldHelper.isThundering(getPlayer()) && !playWhenThundering) {
            return false;
        }
        return WorldHelper.isOutside(getPlayer());
    }

    @Override
    public float getVolumeScaling() {
        var originalScaling = super.getVolumeScaling();
        var cullDistance = BiomeAmbience.cullSoundAboveGround;
        var distanceFromGround = WorldHelper.distanceFromGround(getPlayer(), cullDistance);
        var multiplier = 1.0F - (distanceFromGround / cullDistance);
        var o = originalScaling * Math.max(0.0F, multiplier);
        return o;
    }
}
