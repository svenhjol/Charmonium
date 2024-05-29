package svenhjol.charmonium.feature.world_ambience.client;

import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.charmony.feature.FeatureResolver;
import svenhjol.charmonium.charmony.helper.WorldHelper;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;

public abstract class SurfaceWorldSound extends RepeatedWorldSound implements FeatureResolver<WorldAmbience> {
    protected SurfaceWorldSound(Player player) {
        super(player);
    }

    @Override
    public boolean isValidPlayerCondition() {
        return WorldHelper.isOutside(getPlayer());
    }

    @Override
    public double getVolumeScaling() {
        var cullDistance = feature().cullSoundAboveGround();

        if (cullDistance > 0) {
            return super.getVolumeScaling() * Math.max(0.0d, 1.0d - (WorldHelper.distanceFromGround(getPlayer(), cullDistance) / cullDistance));
        } else {
            return super.getVolumeScaling();
        }
    }

    @Override
    public Class<WorldAmbience> typeForFeature() {
        return WorldAmbience.class;
    }
}
