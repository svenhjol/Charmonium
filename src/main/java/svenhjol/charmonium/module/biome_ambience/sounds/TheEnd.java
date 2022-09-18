package svenhjol.charmonium.module.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.biome_ambience.BiomeSound;
import svenhjol.charmonium.registry.ClientRegistry;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class TheEnd {
    public static SoundEvent THE_END;

    public static Predicate<Holder<Biome>> VALID_BIOME = holder -> holder.is(BiomeTags.IS_END);

    public static void register() {
        THE_END = ClientRegistry.sound("ambience.the_end");
    }

    public static void init(SoundHandler<BiomeSound> handler) {
        // Register The End.
        handler.getSounds().add(new BiomeSound(handler.getPlayer()) {
            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder);
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
