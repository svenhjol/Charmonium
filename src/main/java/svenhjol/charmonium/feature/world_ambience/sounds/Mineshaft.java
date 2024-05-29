 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.core.BlockPos;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.level.block.Blocks;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.charmony.feature.FeatureResolver;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.SoundType;
 import svenhjol.charmonium.feature.world_ambience.client.RepeatedWorldSound;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.feature.world_ambience.client.WorldSound;

 import java.util.Optional;

 public class Mineshaft implements SoundType<WorldSound>, FeatureResolver<WorldAmbience> {
     public final SoundEvent sound;

     public Mineshaft() {
         sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.mineshaft"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!feature().mineshaft()) return;

         handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
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
                 return sound;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(300) + 300;
             }

             @Override
             public float getVolume() {
                 return 0.8f;
             }
         });
     }

     @Override
     public Class<WorldAmbience> typeForFeature() {
         return WorldAmbience.class;
     }
 }
