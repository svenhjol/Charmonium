 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.entity.npc.Villager;
 import net.minecraft.world.phys.AABB;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.ISoundType;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.sound.SurfaceWorldSound;
 import svenhjol.charmonium.sound.WorldSound;
 import svenhjol.charmony.helper.WorldHelper;

 import java.util.List;

 public class Village implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public Village() {
         SOUND = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.village"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.village) return;

         handler.getSounds().add(new SurfaceWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 AABB bb = new AABB(player.blockPosition()).inflate(32);
                 List<Villager> villagers = level.getEntitiesOfClass(Villager.class, bb);

                 if (villagers.size() >= 2) {
                     Villager villager = villagers.get(player.getRandom().nextInt(villagers.size()));
                     setPos(villager.blockPosition());
                     return true;
                 }

                 return false;
             }

             @Override
             public boolean isValidPlayerCondition() {
                 return super.isValidPlayerCondition()
                     && !WorldHelper.isNight(player);
             }

             @Nullable
             @Override
             public SoundEvent getSound() {
                 return SOUND;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(400) + 320;
             }

             @Override
             public float getVolume() {
                 return 0.82F;
             }
         });
     }
 }
