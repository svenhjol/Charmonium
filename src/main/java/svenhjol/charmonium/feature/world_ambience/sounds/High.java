 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.level.Level;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.ISoundType;
 import svenhjol.charmonium.sound.RepeatedWorldSound;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.sound.WorldSound;
 import svenhjol.charmony.helper.WorldHelper;

 public class High implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public High() {
         SOUND = SoundEvent.createVariableRangeEvent(Charmonium.instance().makeId("world.high"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.high) return;

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
                 return SOUND;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(100) + 100;
             }

             @Override
             public float getVolume() {
                 return 0.85F;
             }
         });
     }
 }
