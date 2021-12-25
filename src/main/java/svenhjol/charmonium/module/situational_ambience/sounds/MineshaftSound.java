package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.player_state.PlayerState;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class MineshaftSound extends SituationalSound {
    public static SoundEvent SOUND;
    public static final ResourceLocation MINESHAFT = new ResourceLocation("minecraft:mineshaft");

    private MineshaftSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = ClientRegistry.sound("situational.mineshaft");

        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (!PlayerState.WITHIN_STRUCTURES.contains(MINESHAFT)) {
                // If player state doesn't have a mineshaft then try and detect it using surrounding wood blocks and fence.
                if (!DimensionHelper.isOverworld(level)) return false;
                if (WorldHelper.isOutside(player)) return false;
                if (!WorldHelper.isBelowSeaLevel(player)) return false;

                Optional<BlockPos> optBlock = BlockPos.findClosestMatch(player.blockPosition(), 16, 8, pos -> {
                    Block block = level.getBlockState(pos).getBlock();
                    return block == Blocks.OAK_PLANKS || block == Blocks.OAK_FENCE
                        || block == Blocks.DARK_OAK_PLANKS || block == Blocks.DARK_OAK_FENCE;
                });

                if (optBlock.isEmpty()) return false;
            }

            // Find the closest rail block in the mineshaft.  This will become the sound source.
            Optional<BlockPos> rail = BlockPos.findClosestMatch(player.blockPosition(), 8, 16, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.RAIL;
            });

            if (rail.isPresent()) {
                situation.setPos(rail.get());
                return true;
            }

            return false;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new MineshaftSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(300) + 300;
    }

    @Override
    public float getVolume() {
        return 0.8F;
    }
}