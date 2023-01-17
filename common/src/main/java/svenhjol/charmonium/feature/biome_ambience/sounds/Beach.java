package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charm_core.helper.WorldHelper;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.iface.ISoundType;
import svenhjol.charmonium.init.SoundHandler;
import svenhjol.charmonium.sounds.BiomeSound;
import svenhjol.charmonium.sounds.SurfaceBiomeSound;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;

public class Beach implements ISoundType<BiomeSound> {
    public static SoundEvent DAY_SOUND;
    public static SoundEvent NIGHT_SOUND;
    public static final BiPredicate<Holder<Biome>, ResourceKey<Biome>> VALID_BIOME =
        (holder, key) -> key.equals(Biomes.BEACH) || key.equals(Biomes.STONY_SHORE);

    public Beach() {
        DAY_SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("biome.beach.day"));
        NIGHT_SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("biome.beach.night"));
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
                return VALID_BIOME.test(holder, key);
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
                return VALID_BIOME.test(holder, key);
            }
        });
    }
}
