package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.CharmoniumClient;
import svenhjol.charmonium.sound.BiomeSound;
import svenhjol.charmonium.sound.ISoundType;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.sound.SurfaceBiomeSound;
import svenhjol.charmony.helper.BiomeTagHelper;
import svenhjol.charmony.helper.WorldHelper;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;

public class Plains implements ISoundType<BiomeSound> {
    public static SoundEvent DAY_SOUND;
    public static SoundEvent NIGHT_SOUND;
    public static final BiPredicate<Holder<Biome>, ResourceKey<Biome>> VALID_BIOME =
        (holder, key) -> key.equals(Biomes.PLAINS) || holder.is(BiomeTagHelper.getPlains());

    public Plains() {
        DAY_SOUND = SoundEvent.createVariableRangeEvent(CharmoniumClient.instance().makeId("biome.plains.day"));
        NIGHT_SOUND = SoundEvent.createVariableRangeEvent(CharmoniumClient.instance().makeId("biome.plains.night"));
    }

    @Override
    public void addSounds(SoundHandler<BiomeSound> handler) {
        // Day sound.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), false) {
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
                return VALID_BIOME.test(holder, key);
            }
        });

        // Night sound.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), false) {
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
                return VALID_BIOME.test(holder, key);
            }
        });
    }
}
