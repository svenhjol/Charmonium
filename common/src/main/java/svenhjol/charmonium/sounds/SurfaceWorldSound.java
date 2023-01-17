package svenhjol.charmonium.sounds;

import net.minecraft.world.entity.player.Player;
import svenhjol.charm_core.helper.WorldHelper;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;

public abstract class SurfaceWorldSound extends RepeatedWorldSound {
    protected SurfaceWorldSound(Player player) {
        super(player);
    }

    @Override
    public boolean isValidPlayerCondition() {
        return WorldHelper.isOutside(getPlayer());
    }

    @Override
    public double getVolumeScaling() {
        var cullDistance = WorldAmbience.cullSoundAboveGround;

        if (cullDistance > 0) {
            return super.getVolumeScaling() * Math.max(0.0D, 1.0D - (WorldHelper.distanceFromGround(getPlayer(), cullDistance) / cullDistance));
        } else {
            return super.getVolumeScaling();
        }
    }
}
