package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
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
                var light = level.getMaxLocalRawBrightness(pos);
                var bottom = level.getMinBuildHeight() < 0 ? 0 : 32;

                return SituationalAmbience.VALID_CAVE_DIMENSIONS.contains(level.dimension().location())
                    && !level.canSeeSkyFromBelowWater(pos)
                    && pos.getY() <= bottom
                    && light < SituationalAmbience.CAVE_LIGHT_LEVEL;
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
