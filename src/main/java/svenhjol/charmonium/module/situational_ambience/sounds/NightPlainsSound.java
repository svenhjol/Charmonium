package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.BiomeHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalAmbience;
import svenhjol.charmonium.registry.ClientRegistry;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class NightPlainsSound extends SituationalSound {
    public static SoundEvent SOUND;
    public static final List<BiomeCategory> validCategories = new ArrayList<>();

    private NightPlainsSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);

        validCategories.addAll(Arrays.asList(
            BiomeCategory.PLAINS,
            BiomeCategory.SAVANNA
        ));
    }

    public static void register() {
        SOUND = ClientRegistry.sound("situational.night_plains");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (WorldHelper.isDay(player)) return false;
            if (!WorldHelper.isNearGround(player, SituationalAmbience.cullSoundAboveGround)) return false;
            if (!WorldHelper.isOutside(player)) return false;
            if (WorldHelper.isBelowSeaLevel(player)) return false;

            Biome biome = BiomeHelper.getBiome(level, player.blockPosition());
            BiomeCategory category = biome.getBiomeCategory();
            return validCategories.contains(category);
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new NightPlainsSound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(500) + 500;
    }

    @Override
    public float getVolume() {
        return 0.65F;
    }
}