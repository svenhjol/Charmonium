package svenhjol.charmonium.feature.world_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import svenhjol.charm_core.helper.WorldHelper;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmonium.iface.ISoundType;
import svenhjol.charmonium.init.SoundHandler;
import svenhjol.charmonium.sounds.RepeatedWorldSound;
import svenhjol.charmonium.sounds.WorldSound;

import java.util.Optional;

public class Deepslate implements ISoundType<WorldSound> {
    public static SoundEvent SOUND;

    public Deepslate() {
        SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("world.deepslate"));
    }

    public void addSounds(SoundHandler<WorldSound> handler) {
        if (!WorldAmbience.deepslate) return;

        handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                Optional<BlockPos> optBlock = BlockPos.findClosestMatch(player.blockPosition(), 8, 4, pos -> {
                    Block block = level.getBlockState(pos).getBlock();
                    return block == Blocks.DEEPSLATE;
                });

                return optBlock.isPresent();
            }

            @Override
            public boolean isValidPlayerCondition() {
                return !WorldHelper.isOutside(player)
                    && WorldHelper.isBelowSeaLevel(player);
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return SOUND;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(400) + 400;
            }

            @Override
            public float getVolume() {
                return 0.7F;
            }
        });
    }
}
