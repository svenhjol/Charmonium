package svenhjol.charmonium.sounds;

import net.minecraft.world.entity.player.Player;
import svenhjol.charm_core.helper.WorldHelper;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;

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
    public double getVolumeScaling() {
        var cullDistance = BiomeAmbience.cullSoundAboveGround;

        if (cullDistance > 0) {
            var distanceFromGround = WorldHelper.distanceFromGround(getPlayer(), cullDistance);
            var multiplier = 1.0D - (distanceFromGround / cullDistance);
            return super.getVolumeScaling() * Math.max(0.0D, multiplier);
        } else {
            return super.getVolumeScaling();
        }
    }
}
