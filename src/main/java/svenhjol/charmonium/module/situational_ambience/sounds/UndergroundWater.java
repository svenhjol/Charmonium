package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.RepeatedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.Optional;

public class UndergroundWater {
    public static SoundEvent UNDERGROUND_WATER;

    public static void register() {
        UNDERGROUND_WATER = ClientRegistry.sound("situational.cave_water");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new RepeatedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                Optional<BlockPos> optWater = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                    Block block = level.getBlockState(pos).getBlock();
                    return block == Blocks.WATER;
                });

                if (optWater.isPresent()) {
                    setPos(optWater.get());
                    return true;
                }

                return false;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return !WorldHelper.isOutside(player)
                    && !player.isUnderWater()
                    && WorldHelper.isBelowSeaLevel(player);
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return UNDERGROUND_WATER;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(150) + 120;
            }

            @Override
            public float getVolume() {
                return 0.3F;
            }

            @Override
            public float getPitch() {
                return 0.77F + (0.3F * level.random.nextFloat());
            }
        });
    }
}