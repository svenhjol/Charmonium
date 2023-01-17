 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.entity.npc.Villager;
 import net.minecraft.world.phys.AABB;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charm_core.helper.WorldHelper;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.iface.ISoundType;
 import svenhjol.charmonium.init.SoundHandler;
 import svenhjol.charmonium.sounds.SurfaceWorldSound;
 import svenhjol.charmonium.sounds.WorldSound;

 import java.util.List;

 public class Village implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public Village() {
         SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("world.village"));
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
