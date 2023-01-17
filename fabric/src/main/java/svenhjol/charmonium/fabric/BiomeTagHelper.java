package svenhjol.charmonium.fabric;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

@SuppressWarnings("unused")
public class BiomeTagHelper {
    public static TagKey<Biome> getDesert() {
        return ConventionalBiomeTags.DESERT;
    }

    public static TagKey<Biome> getForest() {
        return ConventionalBiomeTags.FOREST;
    }

    public static TagKey<Biome> getIcy() {
        return ConventionalBiomeTags.ICY;
    }

    public static TagKey<Biome> getJungle() {
        return ConventionalBiomeTags.JUNGLE;
    }

    public static TagKey<Biome> getMountain() {
        return ConventionalBiomeTags.MOUNTAIN;
    }

    public static TagKey<Biome> getOcean() {
        return ConventionalBiomeTags.OCEAN;
    }

    public static TagKey<Biome> getPlains() {
        return ConventionalBiomeTags.PLAINS;
    }

    public static TagKey<Biome> getRiver() {
        return ConventionalBiomeTags.RIVER;
    }

    public static TagKey<Biome> getSnowy() {
        return ConventionalBiomeTags.SNOWY;
    }

    public static TagKey<Biome> getSwamp() {
        return ConventionalBiomeTags.SWAMP;
    }
}
