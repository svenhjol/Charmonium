package svenhjol.charmonium.module.biome_ambience.sounds;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.biome_ambience.BiomeSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.function.Predicate;

public class TheEnd {
    public static SoundEvent THE_END;
    public static final Predicate<Biome> VALID_BIOME = (biome) -> biome.getBiomeCategory() == Biome.BiomeCategory.THEEND;

    public static void register() {
        THE_END = ClientRegistry.sound("ambience.the_end");
    }

    public static void init(SoundHandler<BiomeSound> handler) {

        // Register The End.
        handler.getSounds().add(new BiomeSound(handler.getPlayer()) {
            @Override
            public boolean isValidBiomeCondition(ResourceKey<Biome> biomeKey, Biome biome) {
                return VALID_BIOME.test(biome);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return true;
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return THE_END;
            }
        });
    }
}
