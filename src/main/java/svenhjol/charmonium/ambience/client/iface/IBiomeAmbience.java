package svenhjol.charmonium.ambience.client.iface;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import svenhjol.charmonium.ambience.client.iface.IAmbientSounds;

public interface IBiomeAmbience extends IAmbientSounds {
    @Override
    default boolean isValid() {
        if (getWorld() == null) return false;
        BlockPos pos = getPlayer().getPosition();
        Biome biome = getWorld().getBiome(pos);
        //noinspection ConstantConditions
        if (biome == null) return false;

        return validBiomeConditions(biome.getCategory());
    }

    boolean validBiomeConditions(Biome.Category biomeCategory);
}
