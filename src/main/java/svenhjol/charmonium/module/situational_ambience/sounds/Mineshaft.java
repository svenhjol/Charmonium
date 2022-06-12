package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.player_state.PlayerState;
import svenhjol.charmonium.module.situational_ambience.RepeatedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.Optional;

public class Mineshaft {
    public static SoundEvent MINESHAFT;
    public static final ResourceLocation STRUCTURE = new ResourceLocation("minecraft:mineshaft");

    public static void register() {
        MINESHAFT = ClientRegistry.sound("situational.mineshaft");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new RepeatedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                if (!PlayerState.WITHIN_STRUCTURES.contains(STRUCTURE)) {

                    // If player state doesn't have a mineshaft then try and detect it using surrounding wood blocks and fence.
                    if (!DimensionHelper.isOverworld(level)) return false;
                    if (WorldHelper.isOutside(player)) return false;
                    if (!WorldHelper.isBelowSeaLevel(player)) return false;

                    Optional<BlockPos> optBlock = BlockPos.findClosestMatch(player.blockPosition(), 16, 8, pos -> {
                        var block = level.getBlockState(pos).getBlock();
                        return block == Blocks.OAK_PLANKS || block == Blocks.OAK_FENCE
                            || block == Blocks.DARK_OAK_PLANKS || block == Blocks.DARK_OAK_FENCE;
                    });

                    if (optBlock.isEmpty()) return false;
                }

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