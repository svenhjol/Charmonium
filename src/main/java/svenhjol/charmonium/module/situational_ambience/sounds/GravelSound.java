package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class GravelSound extends SituationalSound {
    public static SoundEvent SOUND;

    private GravelSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = RegistryHelper.sound("situational.gravel");

        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            // basic condition filtering
            if (WorldHelper.isOutside(player)) return false;
            if (!WorldHelper.isBelowSeaLevel(player)) return false;

            Optional<BlockPos> optBlock = BlockPos.findClosestMatch(handler.getPlayer().blockPosition(), 8, 4, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.GRAVEL;
            });

            return optBlock.isPresent();
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new GravelSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(450) + 400;
    }

    @Override
    public float getVolume() {
        return 0.9F;
    }
}