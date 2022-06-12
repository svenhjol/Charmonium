package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.RepeatedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.Optional;

public class Geode {
    public static SoundEvent GEODE;

    public static void register() {
        GEODE = ClientRegistry.sound("situational.geode");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new RepeatedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                Optional<BlockPos> optAmethyst = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                    Block block = level.getBlockState(pos).getBlock();
                    return block instanceof AmethystBlock;
                });

                Optional<BlockPos> optSmoothBasalt = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                    Block block = level.getBlockState(pos).getBlock();
                    return block == Blocks.SMOOTH_BASALT;
                });

                if (optAmethyst.isPresent() && optSmoothBasalt.isPresent()) {
                    setPos(optAmethyst.get());
                    return true;
                }

                return false;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return !WorldHelper.isOutside(player)
                    && WorldHelper.isBelowSeaLevel(player);
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return GEODE;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(350) + 300;
            }

            @Override
            public float getVolume() {
                return 0.5F;
            }

            @Override
            public float getPitch() {
                return 0.8F + (0.4F * level.random.nextFloat());
            }
        });
    }


}