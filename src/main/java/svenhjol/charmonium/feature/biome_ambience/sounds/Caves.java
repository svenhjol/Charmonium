package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.feature.biome_ambience.client.BiomeSound;
import svenhjol.charmonium.sound.SoundType;
import svenhjol.charmonium.sound.SoundHandler;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class Caves implements SoundType<BiomeSound> {
    public final SoundEvent deepDark;
    public final SoundEvent dripstone;
    public final SoundEvent lush;

    public static final Predicate<Holder<Biome>> VALID_BIOME =
        holder -> holder.is(BiomeTags.IS_BEACH);

    public Caves() {
        deepDark = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.caves.deep_dark"));
        dripstone = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.caves.dripstone"));
        lush = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.caves.lush"));
    }

    @Override
    public void addSounds(SoundHandler<BiomeSound> handler) {
        // Deep dark caves.
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
                return deepDark;
            }
        });

        // Dripstone caves.
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
                return dripstone;
            }
        });

        // Lush caves.
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
                return lush;
            }
        });
    }
}
