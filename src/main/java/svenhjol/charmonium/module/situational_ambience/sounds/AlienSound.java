package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.function.Function;
import java.util.function.Predicate;

public class AlienSound extends SituationalSound {
    public static SoundEvent SOUND;

    private AlienSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = RegistryHelper.sound("situational.alien");

        Predicate<SituationalSound> validCondition = situation -> {
            ClientLevel level = situation.getLevel();

            if (!DimensionHelper.isEnd(level))
                return false; // TODO: config for dimensions

            // TODO: random position for effect
            return true;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new AlienSound(handler.getPlayer(), validCondition, soundCondition));
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