 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.charmony.feature.FeatureResolver;
 import svenhjol.charmonium.charmony.helper.WorldHelper;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Plains;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Savanna;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.SoundType;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.feature.world_ambience.client.SurfaceWorldSound;
 import svenhjol.charmonium.feature.world_ambience.client.WorldSound;

 public class NightPlains implements SoundType<WorldSound>, FeatureResolver<WorldAmbience> {
     public final SoundEvent sound;

     public NightPlains() {
         sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.night_plains"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!feature().nightPlains()) return;

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
                 return sound;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(500) + 500;
             }

             @Override
             public float getVolume() {
                 return 0.6f;
             }
         });
     }

     @Override
     public Class<WorldAmbience> typeForFeature() {
         return WorldAmbience.class;
     }
 }
