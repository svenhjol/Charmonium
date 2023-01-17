package svenhjol.charmonium.forge;

import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;

@SuppressWarnings("unused")
public class BiomeTagHelper {
    public static TagKey<Biome> getDesert() {
        return Tags.Biomes.IS_DESERT;
    }

    public static TagKey<Biome> getForest() {
        return BiomeTags.IS_FOREST; // Forge doesn't have an option for this. Maybe custom?
    }

    public static TagKey<Biome> getIcy() {
        return Tags.Biomes.IS_COLD;
    }

    public static TagKey<Biome> getJungle() {
        return BiomeTags.IS_JUNGLE; // Forge doesn't have an option for this. Maybe custom?
    }

    public static TagKey<Biome> getMountain() {
        return Tags.Biomes.IS_MOUNTAIN;
    }

    public static TagKey<Biome> getOcean() {
        return BiomeTags.IS_OCEAN; // Forge doesn't have an option for this. Maybe custom?
    }

    public static TagKey<Biome> getPlains() {
        return Tags.Biomes.IS_PLAINS;
    }

    public static TagKey<Biome> getRiver() {
        return BiomeTags.IS_RIVER; // Forge doesn't have an option for this. Maybe custom?
    }

    public static TagKey<Biome> getSnowy() {
        return Tags.Biomes.IS_SNOWY;
    }

    public static TagKey<Biome> getSwamp() {
        return Tags.Biomes.IS_SWAMP;
    }
}
