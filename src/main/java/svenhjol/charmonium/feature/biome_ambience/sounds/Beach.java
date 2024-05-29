package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.feature.biome_ambience.client.BiomeSound;
import svenhjol.charmonium.sound.SoundType;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.feature.biome_ambience.client.SurfaceBiomeSound;
import svenhjol.charmonium.charmony.helper.WorldHelper;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;

public class Beach implements SoundType<BiomeSound> {
    public final SoundEvent daySound;
    public final SoundEvent nightSound;
    public static final BiPredicate<Holder<Biome>, ResourceKey<Biome>> VALID_BIOME =
        (holder, key) -> key.equals(Biomes.BEACH) || key.equals(Biomes.STONY_SHORE);

    public Beach() {
        daySound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.beach.day"));
        nightSound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.beach.night"));
    }

    @Override
    public void addSounds(SoundHandler<BiomeSound> handler) {
        // Day sound.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), true) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return daySound;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isDay(player);
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder, key);
            }
        });

        // Night sound.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), true) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return nightSound;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isNight(player);
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder, key);
            }
        });
    }
}
