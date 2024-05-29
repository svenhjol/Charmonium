package svenhjol.charmonium.helper;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

@SuppressWarnings("unused")
public class BiomeTagHelper {
    public static TagKey<Biome> getDesert() {
        return ConventionalBiomeTags.IS_DESERT;
    }

    public static TagKey<Biome> getForest() {
        return ConventionalBiomeTags.IS_FOREST;
    }

    public static TagKey<Biome> getIcy() {
        return ConventionalBiomeTags.IS_ICY;
    }

    public static TagKey<Biome> getJungle() {
        return ConventionalBiomeTags.IS_JUNGLE;
    }

    public static TagKey<Biome> getMountain() {
        return ConventionalBiomeTags.IS_MOUNTAIN;
    }

    public static TagKey<Biome> getOcean() {
        return ConventionalBiomeTags.IS_OCEAN;
    }

    public static TagKey<Biome> getPlains() {
        return ConventionalBiomeTags.IS_PLAINS;
    }

    public static TagKey<Biome> getRiver() {
        return ConventionalBiomeTags.IS_RIVER;
    }

    public static TagKey<Biome> getSnowy() {
        return ConventionalBiomeTags.IS_SNOWY;
    }

    public static TagKey<Biome> getSwamp() {
        return ConventionalBiomeTags.IS_SWAMP;
    }
}
