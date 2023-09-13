package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.sound.BiomeSound;
import svenhjol.charmonium.sound.ISoundType;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.sound.SurfaceBiomeSound;
import svenhjol.charmony.helper.BiomeTagHelper;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;

public class River implements ISoundType<BiomeSound> {
    public static SoundEvent SOUND;
    public static BiPredicate<Holder<Biome>, ResourceKey<Biome>> VALID_BIOME =
        (holder, key) -> key.equals(Biomes.RIVER) || holder.is(BiomeTagHelper.getRiver());

    public River() {
        SOUND = SoundEvent.createVariableRangeEvent(Charmonium.instance().makeId("biome.river"));
    }

    @Override
    public void addSounds(SoundHandler<BiomeSound> handler) {
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), true) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return SOUND;
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder, key);
            }
        });
    }
}
