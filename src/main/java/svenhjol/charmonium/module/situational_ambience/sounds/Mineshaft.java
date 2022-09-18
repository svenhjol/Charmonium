package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.situational_ambience.RepeatedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.Optional;

public class Mineshaft {
    public static SoundEvent MINESHAFT;

    public static void register() {
        MINESHAFT = ClientRegistry.sound("situational.mineshaft");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new RepeatedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                // Find the closest rail block in the mineshaft.  This will become the sound source.
                Optional<BlockPos> rail = BlockPos.findClosestMatch(player.blockPosition(), 8, 16, pos -> {
                    var block = level.getBlockState(pos).getBlock();
                    return block == Blocks.RAIL;
                });

                if (rail.isPresent()) {
                    setPos(rail.get());
                    return true;
                }

                return false;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return true;
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return MINESHAFT;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(300) + 300;
            }

            @Override
            public float getVolume() {
                return 0.8F;
            }
        });
    }
}