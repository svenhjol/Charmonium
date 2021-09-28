package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class VillageSound extends SituationalSound {
    public static SoundEvent SOUND;

    private VillageSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = RegistryHelper.sound("situational.village");

        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (WorldHelper.isNight(player)) return false;

            AABB bb = new AABB(player.blockPosition()).inflate(32);
            List<Villager> villagers = level.getEntitiesOfClass(Villager.class, bb);

            if (villagers.size() >= 2) {
                Villager villager = villagers.get(player.getRandom().nextInt(villagers.size()));
                situation.setPos(villager.blockPosition());
                return true;
            }

            return false;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new VillageSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(400) + 320;
    }

    @Override
    public float getVolume() {
        return 0.92F;
    }
}