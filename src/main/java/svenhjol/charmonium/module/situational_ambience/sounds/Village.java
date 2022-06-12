package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.situational_ambience.SurfaceSituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.List;

public class Village {
    public static SoundEvent VILLAGE;

    public static void register() {
        VILLAGE = ClientRegistry.sound("situational.village");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new SurfaceSituationalSound(handler.getPlayer()) {
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
                return VILLAGE;
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