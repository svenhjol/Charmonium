package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.sound.BiomeSound;
import svenhjol.charmonium.sound.ISoundType;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.sound.SurfaceBiomeSound;
import svenhjol.charmony.helper.BiomeTagHelper;
import svenhjol.charmony.helper.WorldHelper;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class Icy implements ISoundType<BiomeSound> {

    public static SoundEvent DAY_SOUND;
    public static SoundEvent NIGHT_SOUND;
    public static final Predicate<Holder<Biome>> VALID_BIOME =
        holder -> holder.is(BiomeTagHelper.getIcy()) || holder.is(BiomeTagHelper.getSnowy());

    public Icy() {
        DAY_SOUND = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.icy.day"));
        NIGHT_SOUND = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.icy.night"));
    }

    @Override
    public void addSounds(SoundHandler<BiomeSound> handler) {
        // Day sound.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), true) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return DAY_SOUND;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isDay(player);
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder);
            }
        });

        // Night sound.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), true) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return NIGHT_SOUND;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isNight(player);
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder);
            }
        });
    }
}
