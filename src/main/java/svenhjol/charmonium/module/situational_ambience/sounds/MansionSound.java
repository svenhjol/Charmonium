package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class MansionSound extends SituationalSound {
    public static SoundEvent SOUND;
    public static int biomeCheckTicks = 100;

    private MansionSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = RegistryHelper.sound("situational.mansion");

        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (!DimensionHelper.isOverworld(level))
                return false;

            // this is potentially expensive so add a longer biome check tick
            if (biomeCheckTicks-- <= 0) {
                if (!level.getBiome(player.blockPosition()).getGenerationSettings().isValidStart(StructureFeatures.WOODLAND_MANSION.feature)) {
                    biomeCheckTicks = 2400; // 2 minutes
                    return false;
                }
            } else {
                return false;
            }

            AABB bb = new AABB(player.blockPosition()).inflate(16);
            List<AbstractIllager> illagers = level.getEntitiesOfClass(AbstractIllager.class, bb);
            if (illagers.size() < 2)
                return false;

            Optional<BlockPos> optBlock = BlockPos.findClosestMatch(player.blockPosition(), 8, 8, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.DARK_OAK_PLANKS;
            });

            if (optBlock.isPresent()) {
                // get an illager's location as the source of the sound
                Optional<AbstractIllager> optIllager = illagers.stream().findAny();
                if (optIllager.isPresent()) {
                    situation.setPos(optIllager.get().blockPosition());
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
        return level.random.nextInt(200) + 200;
    }

    @Override
    public float getVolume() {
        return 0.36F;
    }
}