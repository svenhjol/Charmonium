package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.helper.BiomeTagHelper;
import svenhjol.charmonium.feature.biome_ambience.client.BiomeSound;
import svenhjol.charmonium.sound.SoundType;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.feature.biome_ambience.client.SurfaceBiomeSound;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;

public class River implements SoundType<BiomeSound> {
    public final SoundEvent sound;
    public static BiPredicate<Holder<Biome>, ResourceKey<Biome>> VALID_BIOME =
        (holder, key) -> key.equals(Biomes.RIVER) || holder.is(BiomeTagHelper.getRiver());

    public River() {
        sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "biome.river"));
    }

    @Override
    public void addSounds(SoundHandler<BiomeSound> handler) {
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), true) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return sound;
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder, key);
            }
        });
    }
}
