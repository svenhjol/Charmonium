 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Icy;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Mountains;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.ISoundType;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.sound.SurfaceWorldSound;
 import svenhjol.charmonium.sound.WorldSound;
 import svenhjol.charmony.helper.WorldHelper;

 public class Bleak implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public Bleak() {
         SOUND = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.bleak"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.bleak) return;

         handler.getSounds().add(new SurfaceWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 var holder = getBiomeHolder(player.blockPosition());
                 return Icy.VALID_BIOME.test(holder)
                     || Mountains.VALID_BIOME.test(holder);
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
