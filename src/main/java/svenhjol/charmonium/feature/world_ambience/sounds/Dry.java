 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Badlands;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Desert;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Savanna;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.ISoundType;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.sound.SurfaceWorldSound;
 import svenhjol.charmonium.sound.WorldSound;
 import svenhjol.charmony.helper.WorldHelper;

 public class Dry implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public Dry() {
         SOUND = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.dry"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.dry) return;

         handler.getSounds().add(new SurfaceWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 var holder = getBiomeHolder(player.blockPosition());

                 return Badlands.VALID_BIOME.test(holder)
                     || Desert.VALID_BIOME.test(holder)
                     || Savanna.VALID_BIOME.test(holder);
             }

             @Override
             public boolean isValidPlayerCondition() {
                 return !WorldHelper.isNight(player)
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
                 return level.random.nextInt(750) + 500;
             }

             @Override
             public float getVolume() {
                 return 0.5F;
             }
         });
     }
 }
