 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.level.Level;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.charmony.feature.FeatureResolver;
 import svenhjol.charmonium.charmony.helper.WorldHelper;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.SoundType;
 import svenhjol.charmonium.feature.world_ambience.client.RepeatedWorldSound;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.feature.world_ambience.client.WorldSound;

 public class High implements SoundType<WorldSound>, FeatureResolver<WorldAmbience> {
     public final SoundEvent sound;

     public High() {
         sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.high"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!feature().high()) return;

         handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 int top = level.getMaxBuildHeight() > 256 ? 200 : 150;

                 return level.dimension() == Level.OVERWORLD
                     && player.blockPosition().getY() > top;
             }

             @Override
             public boolean isValidPlayerCondition() {
                 return level.dimension() == Level.OVERWORLD
                     && WorldHelper.isOutside(player);
             }

             @Nullable
             @Override
             public SoundEvent getSound() {
                 return sound;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(200) + 100;
             }

             @Override
             public float getVolume() {
                 return 0.5f;
             }
         });
     }

     @Override
     public Class<WorldAmbience> typeForFeature() {
         return WorldAmbience.class;
     }
 }
