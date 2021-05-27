package svenhjol.charmonium.module.sounds;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

@SuppressWarnings("ConstantConditions")
public interface IBiomeAmbience extends IAmbientSounds {
    @Override
    default boolean isValid() {
        if (getWorld() == null) return false;
        BlockPos pos = getPlayer().getBlockPos();
        Biome biome = getWorld().getBiome(pos);
        if (biome == null) return false;

        return validBiomeConditions(biome.getCategory());
    }

    boolean validBiomeConditions(Biome.Category biomeCategory);
}
