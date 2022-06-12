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

public class Dry {
    public static SoundEvent DRY;

    public static void register() {
        DRY = ClientRegistry.sound("situational.dry");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new SurfaceSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                ResourceKey<Biome> biomeKey = getBiomeKey(player.blockPosition());
                return CharmoniumBiomes.BADLANDS.contains(biomeKey)
                    || CharmoniumBiomes.DESERT.contains(biomeKey)
                    || CharmoniumBiomes.SAVANNA.contains(biomeKey);
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
                return DRY;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(600) + 500;
            }

            @Override
            public float getVolume() {
                return 0.5F;
            }
        });
    }
}