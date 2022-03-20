package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.BiomeHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.init.CharmoniumBiomes;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.function.Function;
import java.util.function.Predicate;

public class SnowstormSound extends SituationalSound {
    public static SoundEvent SOUND;

    private SnowstormSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void register() {
        SOUND = ClientRegistry.sound("situational.snowstorm");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (!WorldHelper.isOutside(player)) return false;
            if (!level.isThundering()) return false;

            var biomeKey = BiomeHelper.getBiomeKey(level, player.blockPosition());
            return CharmoniumBiomes.ICY.contains(biomeKey);
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new SnowstormSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(250) + 250;
    }

    @Override
    public float getVolume() {
        return 0.9F;
    }
}