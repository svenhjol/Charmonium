package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.RepeatedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.List;
import java.util.Optional;

public class Mansion {
    public static SoundEvent MANSION;

    public static void register() {
        MANSION = ClientRegistry.sound("situational.mansion");
    }

    public static void init(SoundHandler<SituationalSound> handler) {

        // Register mansion sound.
        handler.getSounds().add(new RepeatedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
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
                    // Get a hostile mob's location as the source of the sound.
                    Optional<Monster> optMonster = monsters.stream().findAny();
                    if (optMonster.isPresent()) {
                        setPos(optMonster.get().blockPosition());
                        return true;
                    }
                }

                return false;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return !WorldHelper.isBelowSeaLevel(player);
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return MANSION;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(200) + 400;
            }

            @Override
            public float getVolume() {
                return 0.82F;
            }
        });
    }
}