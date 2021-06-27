package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class HighSound extends SituationalSound {
    public static SoundEvent SOUND;

    private HighSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void init(Player player, List<SituationalSound> sounds) {
        SOUND = RegistryHelper.sound("situational.high");

        Predicate<SituationalSound> validCondition = situation -> {
            ClientLevel level = situation.getLevel();

            if (!DimensionHelper.isOverworld(level))
                return false; // TODO: config for dimensions

            if (!WorldHelper.isOutside(player))
                return false;

            int top = level.getMaxBuildHeight() > 256 ? 200 : 150;

            return DimensionHelper.isOverworld(level)
                && player.blockPosition().getY() > top;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        sounds.add(new HighSound(player, validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(250) + 250;
    }

    @Override
    public float getVolume() {
        return 0.35F;
    }
}