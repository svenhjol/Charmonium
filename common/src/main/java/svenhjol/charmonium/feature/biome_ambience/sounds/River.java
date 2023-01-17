package svenhjol.charmonium.feature.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.iface.ISoundType;
import svenhjol.charmonium.init.SoundHandler;
import svenhjol.charmonium.proxy.BiomeTagHelperProxy;
import svenhjol.charmonium.sounds.BiomeSound;
import svenhjol.charmonium.sounds.SurfaceBiomeSound;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;

public class River implements ISoundType<BiomeSound> {
    public static SoundEvent SOUND;
    public static BiPredicate<Holder<Biome>, ResourceKey<Biome>> VALID_BIOME =
        (holder, key) -> key.equals(Biomes.RIVER) || holder.is(BiomeTagHelperProxy.getRiver());

    public River() {
        SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("biome.river"));
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
