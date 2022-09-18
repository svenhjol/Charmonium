package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.biome_ambience.sounds.Badlands;
import svenhjol.charmonium.module.biome_ambience.sounds.Desert;
import svenhjol.charmonium.module.biome_ambience.sounds.Savanna;
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
                var holder = getBiomeHolder(player.blockPosition());

                return Badlands.VALID_BIOME.test(holder)
                    || Desert.VALID_BIOME.test(holder)
                    || Savanna.VALID_BIOME.test(holder);
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