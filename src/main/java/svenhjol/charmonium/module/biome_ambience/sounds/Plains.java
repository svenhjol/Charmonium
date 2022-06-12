package svenhjol.charmonium.module.biome_ambience.sounds;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.init.CharmoniumBiomes;
import svenhjol.charmonium.module.biome_ambience.BiomeSound;
import svenhjol.charmonium.module.biome_ambience.SurfaceBiomeSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.function.Predicate;

public class Plains {
    public static SoundEvent DAY;
    public static SoundEvent NIGHT;
    public static final Predicate<ResourceKey<Biome>> VALID_BIOME = key -> CharmoniumBiomes.PLAINS.contains(key);

    public static void register() {
        DAY = ClientRegistry.sound("ambience.plains.day");
        NIGHT = ClientRegistry.sound("ambience.plains.night");
    }

    public static void init(SoundHandler<BiomeSound> handler) {

        // Register plains day.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), false) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return DAY;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isDay(player);
            }

            @Override
            public boolean isValidBiomeCondition(ResourceKey<Biome> biomeKey, Biome biome) {
                return VALID_BIOME.test(biomeKey);
            }
        });

        // Register plains night.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), false) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return NIGHT;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isNight(player);
            }

            @Override
            public boolean isValidBiomeCondition(ResourceKey<Biome> biomeKey, Biome biome) {
                return VALID_BIOME.test(biomeKey);
            }
        });
    }
}
