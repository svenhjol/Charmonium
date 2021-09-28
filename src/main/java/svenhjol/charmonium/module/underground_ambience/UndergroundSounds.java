package svenhjol.charmonium.module.underground_ambience;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.RegistryHelper;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class UndergroundSounds {
    public static class Cave extends UndergroundSound {
        public static SoundEvent SOUND;

        protected Cave(Player player, Predicate<UndergroundSound> validCondition, Supplier<SoundEvent> soundCondition) {
            super(player, validCondition, soundCondition);
        }

        public static void init(SoundHandler<UndergroundSound> handler) {
            SOUND = RegistryHelper.sound("ambience.cave");

            // config check
            if (!UndergroundAmbience.playCaveAmbience) return;

            // prepare conditions to play the sound
            Predicate<UndergroundSound> validCondition = underground -> {
                Player player = underground.getPlayer();
                ClientLevel level = underground.getLevel();

                if (!UndergroundAmbience.validDimensions.contains(DimensionHelper.getDimension(level))) return false;
                if (player.isUnderWater()) return false;

                BlockPos pos = player.blockPosition();
                int light = level.getMaxLocalRawBrightness(pos);

                if (!level.canSeeSkyFromBelowWater(pos) && pos.getY() <= player.level.getSeaLevel())
                    return pos.getY() <= UndergroundAmbience.caveDepth || light <= UndergroundAmbience.lightLevel;

                return false;
            };

            handler.getSounds().add(new Cave(handler.getPlayer(), validCondition, () -> SOUND));
        }
    }

    public static class DeepCave extends UndergroundSound {
        public static SoundEvent SOUND;

        protected DeepCave(Player player, Predicate<UndergroundSound> validCondition, Supplier<SoundEvent> soundCondition) {
            super(player, validCondition, soundCondition);
        }

        public static void init(SoundHandler<UndergroundSound> handler) {
            SOUND = RegistryHelper.sound("ambience.deep_cave");

            // config check
            if (!UndergroundAmbience.playDeepCaveAmbience) return;

            // prepare conditions to play the sound
            Predicate<UndergroundSound> validCondition = underground -> {
                Player player = underground.getPlayer();
                ClientLevel level = underground.getLevel();

                if (!UndergroundAmbience.validDimensions.contains(DimensionHelper.getDimension(level))) return false;
                if (player.isUnderWater()) return false;

                BlockPos pos = player.blockPosition();
                int light = level.getMaxLocalRawBrightness(pos);
                int bottom = level.getMinBuildHeight() < 0 ? 0 : 32;
                return !level.canSeeSkyFromBelowWater(pos) && pos.getY() <= bottom && light < UndergroundAmbience.lightLevel;
            };

            handler.getSounds().add(new DeepCave(handler.getPlayer(), validCondition, () -> SOUND));
        }
    }
}
