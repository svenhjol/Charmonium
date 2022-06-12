package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.situational_ambience.LoopedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalAmbience;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

public class CaveDrone {
    public static SoundEvent CAVE;

    public static void register() {
        CAVE = ClientRegistry.sound("ambience.cave");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new LoopedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                BlockPos pos = player.blockPosition();
                int light = level.getMaxLocalRawBrightness(pos);

                if (!level.canSeeSkyFromBelowWater(pos) && pos.getY() <= player.level.getSeaLevel()) {
                    return pos.getY() <= 48 || light <= SituationalAmbience.CAVE_LIGHT_LEVEL;
                }

                return false;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return !player.isUnderWater();
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return CAVE;
            }
        });
    }
}
