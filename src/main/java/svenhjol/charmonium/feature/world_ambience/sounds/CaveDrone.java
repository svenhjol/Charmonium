package svenhjol.charmonium.feature.world_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.CharmoniumClient;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmonium.sound.ISoundType;
import svenhjol.charmonium.sound.LoopedWorldSound;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.sound.WorldSound;

public class CaveDrone implements ISoundType<WorldSound> {
    public static SoundEvent SOUND;

    public CaveDrone() {
        SOUND = SoundEvent.createVariableRangeEvent(CharmoniumClient.instance().makeId("world.cave"));
    }

    public void addSounds(SoundHandler<WorldSound> handler) {
        if (!WorldAmbience.caveDrone) return;

        handler.getSounds().add(new LoopedWorldSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                BlockPos pos = player.blockPosition();
                int light = level.getMaxLocalRawBrightness(pos);

                if (!WorldAmbience.VALID_CAVE_DIMENSIONS.contains(level.dimension().location())) {
                    return false;
                }

                if (!level.canSeeSkyFromBelowWater(pos) && pos.getY() <= player.level().getSeaLevel()) {
                    return pos.getY() <= 48 || light <= WorldAmbience.CAVE_LIGHT_LEVEL;
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
                return SOUND;
            }
        });
    }
}
