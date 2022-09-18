package svenhjol.charmonium.module.biome_ambience.sounds;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.biome_ambience.BiomeSound;
import svenhjol.charmonium.module.biome_ambience.SurfaceBiomeSound;
import svenhjol.charmonium.registry.ClientRegistry;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class River {
    public static SoundEvent RIVER;
    public static final Predicate<Holder<Biome>> VALID_BIOME = holder -> holder.is(BiomeTags.IS_RIVER)
        || holder.is(ConventionalBiomeTags.RIVER);

    public static void register() {
        RIVER = ClientRegistry.sound("ambience.river");
    }

    public static void init(SoundHandler<BiomeSound> handler) {
        // Register river.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), true) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return RIVER;
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder);
            }
        });
    }
}
