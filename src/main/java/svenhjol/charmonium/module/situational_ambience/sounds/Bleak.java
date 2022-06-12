package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.init.CharmoniumBiomes;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.module.situational_ambience.SurfaceSituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

public class Bleak {
    public static SoundEvent BLEAK;

    public static void register() {
        BLEAK = ClientRegistry.sound("situational.bleak");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new SurfaceSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                ResourceKey<Biome> biomeKey = getBiomeKey(player.blockPosition());
                return CharmoniumBiomes.ICY.contains(biomeKey)
                    || CharmoniumBiomes.MOUNTAIN.contains(biomeKey);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return !WorldHelper.isNight(player)
                    && WorldHelper.isOutside(player)
                    && !WorldHelper.isBelowSeaLevel(player);
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return BLEAK;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(600) + 500;
            }

            @Override
            public float getVolume() {
                return 0.6F;
            }
        });
    }

}