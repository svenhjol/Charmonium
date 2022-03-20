package svenhjol.charmonium.init;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.helper.LogHelper;

import java.util.ArrayList;
import java.util.List;

public class CharmoniumBiomes {
    public static List<ResourceKey<Biome>> BADLANDS = new ArrayList<>();
    public static List<ResourceKey<Biome>> BEACH = new ArrayList<>();
    public static List<ResourceKey<Biome>> DESERT = new ArrayList<>();
    public static List<ResourceKey<Biome>> FOREST = new ArrayList<>();
    public static List<ResourceKey<Biome>> ICY = new ArrayList<>();
    public static List<ResourceKey<Biome>> JUNGLE = new ArrayList<>();
    public static List<ResourceKey<Biome>> MOUNTAIN = new ArrayList<>();
    public static List<ResourceKey<Biome>> OCEAN = new ArrayList<>();
    public static List<ResourceKey<Biome>> PLAINS = new ArrayList<>();
    public static List<ResourceKey<Biome>> RIVER = new ArrayList<>();
    public static List<ResourceKey<Biome>> SAVANNA = new ArrayList<>();
    public static List<ResourceKey<Biome>> SWAMP = new ArrayList<>();
    public static List<ResourceKey<Biome>> TAIGA = new ArrayList<>();
    public static List<ResourceKey<Biome>> THEEND = new ArrayList<>();

    public static void init() {
        BADLANDS.addAll(List.of(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS));
        BEACH.addAll(List.of(Biomes.BEACH, Biomes.STONY_SHORE));
        DESERT.addAll(List.of(Biomes.DESERT));
        FOREST.addAll(List.of(Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.DARK_FOREST, Biomes.GROVE, Biomes.WINDSWEPT_FOREST));
        ICY.addAll(List.of(Biomes.SNOWY_BEACH, Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES, Biomes.FROZEN_RIVER));
        JUNGLE.addAll(List.of(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE));
        MOUNTAIN.addAll(List.of(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS, Biomes.STONY_PEAKS, Biomes.SNOWY_SLOPES, Biomes.WINDSWEPT_GRAVELLY_HILLS));
        OCEAN.addAll(List.of(Biomes.OCEAN, Biomes.COLD_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.FROZEN_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_FROZEN_OCEAN));
        PLAINS.addAll(List.of(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.WINDSWEPT_HILLS, Biomes.MEADOW));
        RIVER.addAll(List.of(Biomes.RIVER));
        SAVANNA.addAll(List.of(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA));
        SWAMP.addAll(List.of(Biomes.SWAMP, Biomes.MUSHROOM_FIELDS));
        TAIGA.addAll(List.of(Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA));
        THEEND.addAll(List.of(Biomes.THE_END, Biomes.THE_VOID, Biomes.END_BARRENS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.SMALL_END_ISLANDS));
    }

    public static void tryAddBiome(String id, List<ResourceKey<Biome>> category) {
        var biome = BuiltinRegistries.BIOME.get(new ResourceLocation(id));
        if (biome == null) return;

        var biomeKey = BuiltinRegistries.BIOME.getResourceKey(biome).orElse(null);
        if (biomeKey == null) return;

        if (!category.contains(biomeKey)) {
            category.add(biomeKey);
            LogHelper.info(CharmoniumBiomes.class, "Added " + biomeKey + " to biome collection " + category);
        }
    }
}
