 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.core.BlockPos;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.level.block.AmethystBlock;
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

 public class Geode implements SoundType<WorldSound>, FeatureResolver<WorldAmbience> {
     public final SoundEvent sound;

     public Geode() {
         sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.geode"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!feature().geode()) return;

         handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 Optional<BlockPos> optAmethyst = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                     Block block = level.getBlockState(pos).getBlock();
                     return block instanceof AmethystBlock;
                 });

                 Optional<BlockPos> optSmoothBasalt = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                     Block block = level.getBlockState(pos).getBlock();
                     return block == Blocks.SMOOTH_BASALT;
                 });

                 if (optAmethyst.isPresent() && optSmoothBasalt.isPresent()) {
                     setPos(optAmethyst.get());
                     return true;
                 }

                 return false;
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
                 return level.random.nextInt(350) + 300;
             }

             @Override
             public float getVolume() {
                 return 0.5f;
             }

             @Override
             public float getPitch() {
                 return 0.8f + (0.4f * level.random.nextFloat());
             }
         });
     }

     @Override
     public Class<WorldAmbience> typeForFeature() {
         return WorldAmbience.class;
     }
 }
