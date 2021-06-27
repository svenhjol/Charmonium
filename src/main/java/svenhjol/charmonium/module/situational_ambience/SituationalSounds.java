package svenhjol.charmonium.module.situational_ambience;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class SituationalSounds {
    public static class Mineshaft extends SituationalSound {
        public static SoundEvent SOUNDS;
        public static double distance = -1;

        private Mineshaft(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
            super(player, validCondition, soundCondition);
        }

        public static void init(Player player, List<SituationalSound> sounds) {
            SOUNDS = RegistryHelper.sound("situational.mineshaft");

            Predicate<SituationalSound> validCondition = situation -> {
                ClientLevel level = situation.getLevel();
                if (WorldHelper.isOutside(player))
                    return false;

                Optional<BlockPos> optBlock = BlockPos.findClosestMatch(player.blockPosition(), 32, 16, pos -> {
                    Block block = level.getBlockState(pos).getBlock();
                    return block == Blocks.OAK_PLANKS || block == Blocks.OAK_FENCE;
                });

                Optional<BlockPos> optRail = BlockPos.findClosestMatch(player.blockPosition(), 32, 16, pos -> {
                    Block block = level.getBlockState(pos).getBlock();
                    return block == Blocks.RAIL;
                });

                if (optBlock.isPresent() && optRail.isPresent()) {
                    double distBlocks = WorldHelper.getDistanceSquared(player.blockPosition(), optBlock.get());
                    double distRail = WorldHelper.getDistanceSquared(player.blockPosition(), optRail.get());
                    distance = Math.min(distBlocks, distRail);
                    return true;
                }

                return false;
            };

            Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUNDS;
            sounds.add(new Mineshaft(player, validCondition, soundCondition));
        }

        @Override
        public float getVolume() {
            // TODO: scale with distance
            return super.getVolume();
        }
    }
}
