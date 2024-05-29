package svenhjol.charmonium.feature.biome_ambience.client;

import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.charmony.feature.FeatureResolver;
import svenhjol.charmonium.charmony.helper.WorldHelper;
import svenhjol.charmonium.feature.biome_ambience.BiomeAmbience;

public abstract class SurfaceBiomeSound extends BiomeSound implements FeatureResolver<BiomeAmbience> {
    protected boolean playWhenThundering;

    protected SurfaceBiomeSound(Player player, boolean playWhenThundering) {
        super(player);
        this.playWhenThundering = playWhenThundering;
    }

    @Override
    public Class<BiomeAmbience> typeForFeature() {
        return BiomeAmbience.class;
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
        var cullDistance = feature().cullSoundAboveGround();

        if (cullDistance > 0) {
            var distanceFromGround = WorldHelper.distanceFromGround(getPlayer(), cullDistance);
            var multiplier = 1.0d - (distanceFromGround / cullDistance);
            return super.getVolumeScaling() * Math.max(0.0d, multiplier);
        } else {
            return super.getVolumeScaling();
        }
    }
}
