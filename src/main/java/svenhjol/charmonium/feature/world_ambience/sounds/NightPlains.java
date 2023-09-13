 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Plains;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Savanna;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.ISoundType;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.sound.SurfaceWorldSound;
 import svenhjol.charmonium.sound.WorldSound;
 import svenhjol.charmony.helper.WorldHelper;

 public class NightPlains implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public NightPlains() {
         SOUND = SoundEvent.createVariableRangeEvent(Charmonium.instance().makeId("world.nightplains"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.nightPlains) return;

         handler.getSounds().add(new SurfaceWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 var holder = getBiomeHolder(player.blockPosition());
                 var key = getBiomeKey(player.blockPosition());
                 return Plains.VALID_BIOME.test(holder, key)
                     || Savanna.VALID_BIOME.test(holder);
             }

             @Override
             public boolean isValidPlayerCondition() {
                 return WorldHelper.isNight(player)
                     && WorldHelper.isOutside(player)
                     && !WorldHelper.isBelowSeaLevel(player);
             }

             @Nullable
             @Override
             public SoundEvent getSound() {
                 return SOUND;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(500) + 500;
             }

             @Override
             public float getVolume() {
                 return 0.6F;
             }
         });
     }
 }
