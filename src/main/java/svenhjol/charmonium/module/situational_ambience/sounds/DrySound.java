package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class DrySound extends SituationalSound {
    public static SoundEvent SOUND;
    public static final List<BiomeCategory> validCategories = new ArrayList<>();

    private DrySound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        super(player, validCondition, soundCondition);

        validCategories.addAll(Arrays.asList(
            BiomeCategory.DESERT,
            BiomeCategory.MESA,
            BiomeCategory.SAVANNA
        ));
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        SOUND = RegistryHelper.sound("situational.dry");

        Predicate<SituationalSound> validCondition = situation -> {
            Player player = situation.getPlayer();
            ClientLevel level = situation.getLevel();

            if (!WorldHelper.isOutside(player)) return false;
            if (WorldHelper.isNight(player)) return false;
            if (WorldHelper.isBelowSeaLevel(player)) return false;

            Biome biome = level.getBiome(player.blockPosition());
            BiomeCategory category = biome.getBiomeCategory();
            return validCategories.contains(category);
        };

        Function<SituationalSound, SoundEvent> soundCondition = situation -> SOUND;
        handler.getSounds().add(new DrySound(handler.getPlayer(), validCondition, soundCondition));
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(600) + 500;
    }

    @Override
    public float getVolume() {
        return 0.7F;
    }
}