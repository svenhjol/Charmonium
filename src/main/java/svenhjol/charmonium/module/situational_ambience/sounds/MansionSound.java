package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class MansionSound extends SituationalSound {
    public static SoundEvent SOUND;
    public static int baseDelay = 400;

    private MansionSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = RegistryHelper.sound("situational.mansion");

        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (WorldHelper.isBelowSeaLevel(player)) return false;

            AABB bb = new AABB(player.blockPosition()).inflate(10);
            List<Monster> monsters = level.getEntitiesOfClass(Monster.class, bb);

            Optional<BlockPos> optBlock1 = BlockPos.findClosestMatch(player.blockPosition(), 8, 8, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.DARK_OAK_PLANKS;
            });

            Optional<BlockPos> optBlock2 = BlockPos.findClosestMatch(player.blockPosition(), 8, 8, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.BIRCH_PLANKS;
            });

            if (optBlock1.isPresent() && optBlock2.isPresent()) {
                // get a hostile mob's location as the source of the sound
                Optional<Monster> optMonster = monsters.stream().findAny();
                if (optMonster.isPresent()) {
                    situation.setPos(optMonster.get().blockPosition());
                    return true;
                }
            }

            return false;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new MansionSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(200) + baseDelay;
    }

    @Override
    public float getVolume() {
        return 0.82F;
    }
}