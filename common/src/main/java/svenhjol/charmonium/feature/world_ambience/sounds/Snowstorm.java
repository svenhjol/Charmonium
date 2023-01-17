 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charm_core.helper.WorldHelper;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.biome_ambience.sounds.Icy;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.iface.ISoundType;
 import svenhjol.charmonium.init.SoundHandler;
 import svenhjol.charmonium.sounds.RepeatedWorldSound;
 import svenhjol.charmonium.sounds.WorldSound;

 public class Snowstorm implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public Snowstorm() {
         SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("world.snowstorm"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.snowstorm) return;

         handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 var holder = getBiomeHolder(player.blockPosition());
                 return Icy.VALID_BIOME.test(holder);
             }

             @Override
             public boolean isValidPlayerCondition() {
                 return WorldHelper.isOutside(player)
                     && getLevel().isThundering();
             }

             @Nullable
             @Override
             public SoundEvent getSound() {
                 return SOUND;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(250) + 250;
             }

             @Override
             public float getVolume() {
                 return 0.9F;
             }
         });
     }
 }
