package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.registry.ClientRegistry;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.function.Function;
import java.util.function.Predicate;

public class HighSound extends SituationalSound {
    public static SoundEvent SOUND;

    private HighSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void register() {
        SOUND = ClientRegistry.sound("situational.high");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (!DimensionHelper.isOverworld(level)) return false;
            if (!WorldHelper.isOutside(player)) return false;

            int top = level.getMaxBuildHeight() > 256 ? 200 : 150;
            return DimensionHelper.isOverworld(level) && player.blockPosition().getY() > top;
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new HighSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(100) + 100;
    }

    @Override
    public float getVolume() {
        return 0.9F;
    }
}