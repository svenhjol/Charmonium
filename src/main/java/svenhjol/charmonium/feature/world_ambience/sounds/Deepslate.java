package svenhjol.charmonium.feature.world_ambience.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.charmony.feature.FeatureResolver;
import svenhjol.charmonium.charmony.helper.WorldHelper;
import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
import svenhjol.charmonium.sound.SoundType;
import svenhjol.charmonium.feature.world_ambience.client.RepeatedWorldSound;
import svenhjol.charmonium.sound.SoundHandler;
import svenhjol.charmonium.feature.world_ambience.client.WorldSound;

import java.util.Optional;

public class Deepslate implements SoundType<WorldSound>, FeatureResolver<WorldAmbience> {
    public final SoundEvent sound;

    public Deepslate() {
        sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.deepslate"));
    }

    public void addSounds(SoundHandler<WorldSound> handler) {
        if (!feature().deepslate()) return;

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
                return sound;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(400) + 400;
            }

            @Override
            public float getVolume() {
                return 0.7f;
            }
        });
    }

    @Override
    public Class<WorldAmbience> typeForFeature() {
        return WorldAmbience.class;
    }
}
