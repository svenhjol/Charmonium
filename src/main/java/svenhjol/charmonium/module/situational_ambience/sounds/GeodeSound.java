package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class GeodeSound extends SituationalSound {
    public static SoundEvent SOUND;

    private GeodeSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = RegistryHelper.sound("situational.geode");

        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (WorldHelper.isOutside(player)) return false;
            if (!WorldHelper.isBelowSeaLevel(player)) return false;

            Optional<BlockPos> optAmethyst = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block instanceof AmethystBlock;
            });

            Optional<BlockPos> optSmoothBasalt = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.SMOOTH_BASALT;
            });

            if (optAmethyst.isPresent() && optSmoothBasalt.isPresent()) {
                situation.setPos(optAmethyst.get());
                return true;
            }

            return false;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new GeodeSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(350) + 300;
    }

    @Override
    public float getVolume() {
        return 0.7F;
    }

    @Override
    public float getPitch() {
        return 0.8F + (0.4F * level.random.nextFloat());
    }
}