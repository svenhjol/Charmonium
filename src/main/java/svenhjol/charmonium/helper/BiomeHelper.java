package svenhjol.charmonium.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;

public class BiomeHelper {
    public static Biome getBiome(Level level, BlockPos pos) {
        return getBiomeHolder(level, pos).value();
    }

    public static Holder<Biome> getBiomeHolder(Level level, BlockPos pos) {
        return level.getBiomeManager().getBiome(pos);
    }

    @Nullable
    public static ResourceKey<Biome> getBiomeKey(Level level, BlockPos pos) {
        var biome = getBiome(level, pos);
        return level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getResourceKey(biome).orElse(null);
    }


}
