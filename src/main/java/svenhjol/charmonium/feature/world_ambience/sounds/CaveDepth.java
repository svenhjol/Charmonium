package svenhjol.charmonium.feature.world_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.CharmoniumClient;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmonium.sound.ISoundType;
import svenhjol.charmonium.sound.LoopedWorldSound;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.sound.WorldSound;

public class CaveDepth implements ISoundType<WorldSound> {
    public static SoundEvent SOUND;

    public CaveDepth() {
        SOUND = SoundEvent.createVariableRangeEvent(CharmoniumClient.instance().makeId("world.deep_cave"));
    }

    @Override
    public void addSounds(SoundHandler<WorldSound> handler) {
        if (!WorldAmbience.caveDepth) return;

        handler.getSounds().add(new LoopedWorldSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                var pos = player.blockPosition();

                // Don't play this if the player is in the Deep Dark, the combined sounds are too intense.
                var key = getBiomeKey(pos);
                if (key == Biomes.DEEP_DARK) {
                    return false;
                }

                if (!WorldAmbience.VALID_CAVE_DIMENSIONS.contains(level.dimension().location())) {
                    return false;
                }

                var light = level.getMaxLocalRawBrightness(pos);
                var bottom = level.getMinBuildHeight() < 0 ? 0 : 32;
                return !level.canSeeSkyFromBelowWater(pos)
                    && pos.getY() <= bottom
                    && light < WorldAmbience.CAVE_LIGHT_LEVEL;
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
