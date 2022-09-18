package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.situational_ambience.LoopedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalAmbience;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

public class CaveDepth {
    public static SoundEvent DEEP_CAVE;

    public static void register() {
        DEEP_CAVE = ClientRegistry.sound("ambience.deep_cave");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new LoopedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                var pos = player.blockPosition();

                // Don't play this if the player is in the Deep Dark, the combined sounds are too intense.
                var key = getBiomeKey(pos);
                if (key == Biomes.DEEP_DARK) {
                    return false;
                }

                if (!SituationalAmbience.VALID_CAVE_DIMENSIONS.contains(level.dimension().location())) {
                    return false;
                }

                var light = level.getMaxLocalRawBrightness(pos);
                var bottom = level.getMinBuildHeight() < 0 ? 0 : 32;
                return !level.canSeeSkyFromBelowWater(pos) && pos.getY() <= bottom && light < SituationalAmbience.CAVE_LIGHT_LEVEL;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return !player.isUnderWater();
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return DEEP_CAVE;
            }
        });
    }
}
