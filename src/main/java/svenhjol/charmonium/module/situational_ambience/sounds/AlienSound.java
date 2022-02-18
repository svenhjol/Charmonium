package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

import java.util.function.Function;
import java.util.function.Predicate;

public class AlienSound extends SituationalSound {
    public static SoundEvent SOUND;

    private AlienSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);
    }

    public static void register() {
        SOUND = ClientRegistry.sound("situational.alien");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        Predicate<SituationalSound> validCondition = situation -> {
            ClientLevel level = situation.getLevel();
            Player player = situation.getPlayer();
            return level.getBiome(player.blockPosition()).value().getBiomeCategory() == Biome.BiomeCategory.THEEND;
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
        return 0.85F;
    }
}