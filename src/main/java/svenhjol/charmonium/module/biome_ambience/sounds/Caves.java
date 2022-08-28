package svenhjol.charmonium.module.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.biome_ambience.BiomeSound;
import svenhjol.charmonium.registry.ClientRegistry;

import javax.annotation.Nullable;

public class Caves {
    public static SoundEvent DRIPSTONE;
    public static SoundEvent LUSH;
    public static SoundEvent DEEP_DARK;

    public static void register() {
        DRIPSTONE = ClientRegistry.sound("ambience.caves.dripstone");
        LUSH = ClientRegistry.sound("ambience.caves.lush");
        DEEP_DARK = ClientRegistry.sound("ambience.caves.deep_dark");
    }

    public static void init(SoundHandler<BiomeSound> handler) {
        // Register dripstone caves.
        handler.getSounds().add(new BiomeSound(handler.getPlayer()) {
            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return key.equals(Biomes.DRIPSTONE_CAVES);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return true;
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return DRIPSTONE;
            }
        });

        // Register lush caves.
        handler.getSounds().add(new BiomeSound(handler.getPlayer()) {
            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return key.equals(Biomes.LUSH_CAVES);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return true;
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return LUSH;
            }
        });

        // Register deep dark caves.
        handler.getSounds().add(new BiomeSound(handler.getPlayer()) {
            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return key.equals(Biomes.DEEP_DARK);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return true;
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return DEEP_DARK;
            }
        });
    }
}
