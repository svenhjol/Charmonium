 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.charmony.feature.FeatureResolver;
 import svenhjol.charmonium.charmony.helper.WorldHelper;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Badlands;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Desert;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Savanna;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.SoundType;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.feature.world_ambience.client.SurfaceWorldSound;
 import svenhjol.charmonium.feature.world_ambience.client.WorldSound;

 public class Dry implements SoundType<WorldSound>, FeatureResolver<WorldAmbience> {
     public final SoundEvent sound;

     public Dry() {
         sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.dry"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!feature().dry()) return;

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
                 return sound;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(750) + 500;
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
