package svenhjol.charmonium.module.biome_ambience;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.registry.ClientRegistry;
import svenhjol.charmonium.helper.WorldHelper;

import java.util.function.BiPredicate;

public class BiomeSounds {
    public static class Beach {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.beach.day");
            NIGHT = ClientRegistry.sound("ambience.beach.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.BEACH && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Badlands {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.badlands.day");
            NIGHT = ClientRegistry.sound("ambience.badlands.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.MESA && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Caves {
        public static SoundEvent DRIPSTONE;
        public static SoundEvent LUSH;

        public static void register() {
            DRIPSTONE = ClientRegistry.sound("ambience.caves.dripstone");
            LUSH = ClientRegistry.sound("ambience.caves.lush");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> dripstoneCavesCondition = (biomeKey, biome)
                -> biomeKey.equals(Biomes.DRIPSTONE_CAVES);

            BiPredicate<ResourceKey<Biome>, Biome> lushCavesCondition = (biomeKey, biome)
                -> biomeKey.equals(Biomes.LUSH_CAVES);

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), dripstoneCavesCondition, () -> DRIPSTONE));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), lushCavesCondition, () -> LUSH));
        }
    }

    public static class Desert {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.desert.day");
            NIGHT = ClientRegistry.sound("ambience.desert.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.DESERT && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Forest {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.forest.day");
            NIGHT = ClientRegistry.sound("ambience.forest.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.FOREST
                && WorldHelper.isOutside(handler.getPlayer())
                && !WorldHelper.isThundering(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Icy {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.icy.day");
            NIGHT = ClientRegistry.sound("ambience.icy.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> (biome.getBiomeCategory() == BiomeCategory.ICY
                || biome.getBiomeCategory() == BiomeCategory.EXTREME_HILLS)
                && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Jungle {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.jungle.day");
            NIGHT = ClientRegistry.sound("ambience.jungle.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.JUNGLE && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Mountains {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.mountains.day");
            NIGHT = ClientRegistry.sound("ambience.mountains.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.MOUNTAIN && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Ocean {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.ocean.day");
            NIGHT = ClientRegistry.sound("ambience.ocean.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.OCEAN && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Plains {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.plains.day");
            NIGHT = ClientRegistry.sound("ambience.plains.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.PLAINS
                && WorldHelper.isOutside(handler.getPlayer())
                && !WorldHelper.isThundering(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class River {
        public static SoundEvent RIVER;

        public static void register() {
            RIVER = ClientRegistry.sound("ambience.river");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.RIVER
                && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition, () -> RIVER));
        }
    }

    public static class Savanna {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.savanna.day");
            NIGHT = ClientRegistry.sound("ambience.savanna.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.SAVANNA && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Swamp {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.swamp.day");
            NIGHT = ClientRegistry.sound("ambience.swamp.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> (biome.getBiomeCategory() == BiomeCategory.SWAMP
                    || biome.getBiomeCategory() == BiomeCategory.MUSHROOM)
                && WorldHelper.isOutside(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class Taiga {
        public static SoundEvent DAY;
        public static SoundEvent NIGHT;

        public static void register() {
            DAY = ClientRegistry.sound("ambience.taiga.day");
            NIGHT = ClientRegistry.sound("ambience.taiga.night");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.TAIGA
                && WorldHelper.isOutside(handler.getPlayer())
                && !WorldHelper.isThundering(handler.getPlayer());

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isDay(handler.getPlayer())), () -> DAY));
            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition.and((k, b) -> WorldHelper.isNight(handler.getPlayer())), () -> NIGHT));
        }
    }

    public static class TheEnd {
        public static SoundEvent SOUND;

        public static void register() {
            SOUND = ClientRegistry.sound("ambience.the_end");
        }

        public static void init(SoundHandler<BiomeSound> handler) {
            BiPredicate<ResourceKey<Biome>, Biome> biomeCondition = (biomeKey, biome)
                -> biome.getBiomeCategory() == BiomeCategory.THEEND;

            handler.getSounds().add(new BiomeSound(handler.getPlayer(), biomeCondition, () -> SOUND));
        }
    }
}
