package svenhjol.charmonium.module.biome_ambience;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;

import java.util.List;
import java.util.function.Predicate;

public class BiomeSounds {
    public static class Beach {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.beach.day");
            NIGHT = RegistryHelper.sound("biomes.beach.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.BEACH && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Badlands {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.badlands.day");
            NIGHT = RegistryHelper.sound("biomes.badlands.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.MESA && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Desert {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.desert.day");
            NIGHT = RegistryHelper.sound("biomes.desert.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.DESERT && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Forest {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.forest.day");
            NIGHT = RegistryHelper.sound("biomes.forest.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.FOREST && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Icy {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.icy.day");
            NIGHT = RegistryHelper.sound("biomes.icy.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.ICY && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Jungle {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.jungle.day");
            NIGHT = RegistryHelper.sound("biomes.jungle.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.JUNGLE && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Mountains {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.mountains.day");
            NIGHT = RegistryHelper.sound("biomes.mountains.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.EXTREME_HILLS && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Ocean {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.ocean.day");
            NIGHT = RegistryHelper.sound("biomes.ocean.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.OCEAN && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Plains {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.plains.day");
            NIGHT = RegistryHelper.sound("biomes.plains.night");

            Predicate<Biome> biomeCondition = biome
                -> (biome.getBiomeCategory() == BiomeCategory.PLAINS
                    || biome.getBiomeCategory() == BiomeCategory.RIVER)
                && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Savanna {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.savanna.day");
            NIGHT = RegistryHelper.sound("biomes.savanna.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.SAVANNA && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Swamp {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.swamp.day");
            NIGHT = RegistryHelper.sound("biomes.swamp.night");

            Predicate<Biome> biomeCondition = biome
                -> (biome.getBiomeCategory() == BiomeCategory.SWAMP
                    || biome.getBiomeCategory() == BiomeCategory.MUSHROOM)
                && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }

    public static class Taiga {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void init(Player player, List<BiomeSound> sounds) {
            DAY = RegistryHelper.sound("biomes.taiga.day");
            NIGHT = RegistryHelper.sound("biomes.taiga.night");

            Predicate<Biome> biomeCondition = biome
                -> biome.getBiomeCategory() == BiomeCategory.TAIGA && WorldHelper.isOutside(player);

            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isDay(player)), () -> DAY));
            sounds.add(new BiomeSound(player, biomeCondition.and(b -> WorldHelper.isNight(player)), () -> NIGHT));
        }
    }
}
