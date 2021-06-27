package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class AlienSound extends SituationalSound {
    public static SoundEvent SOUND;

    private AlienSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(Player player, List<SituationalSound> sounds) {
        SOUND = RegistryHelper.sound("situational.alien");

        Predicate<SituationalSound> validCondition = situation -> {
            ClientLevel level = situation.getLevel();

            if (!DimensionHelper.isEnd(situation.getLevel()))
                return false; // TODO: config for dimensions

            // TODO: random position for effect
            return true;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        sounds.add(new AlienSound(player, validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(400) + 300;
    }

    @Override
    public float getVolume() {
        return 0.45F;
    }
}