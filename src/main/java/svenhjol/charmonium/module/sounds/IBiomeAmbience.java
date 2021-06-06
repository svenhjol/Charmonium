package svenhjol.charmonium.module.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;

@SuppressWarnings("ConstantConditions")
public interface IBiomeAmbience extends IAmbientSounds {
    @Override
    default boolean isValid() {
        if (getWorld() == null) return false;
        BlockPos pos = getPlayer().blockPosition();
        Biome biome = getWorld().getBiome(pos);
        if (biome == null) return false;

        return validBiomeConditions(biome.getBiomeCategory());
    }

    boolean validBiomeConditions(Biome.BiomeCategory biomeCategory);
}
