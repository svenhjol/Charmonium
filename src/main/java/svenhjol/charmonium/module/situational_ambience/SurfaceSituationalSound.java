package svenhjol.charmonium.module.situational_ambience;

import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.helper.WorldHelper;

public abstract class SurfaceSituationalSound extends RepeatedSituationalSound {
    protected SurfaceSituationalSound(Player player) {
        super(player);
    }

    @Override
    public boolean isValidPlayerCondition() {
        return WorldHelper.isOutside(getPlayer());
    }

    @Override
    public float getVolumeScaling() {
        var cullDistance = SituationalAmbience.cullSoundAboveGround;
        return super.getVolumeScaling() * Math.max(0.0F, 1.0F - (WorldHelper.distanceFromGround(getPlayer(), cullDistance) / cullDistance));
    }
}
