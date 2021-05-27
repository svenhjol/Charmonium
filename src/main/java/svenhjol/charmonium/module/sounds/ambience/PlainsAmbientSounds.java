package svenhjol.charmonium.module.sounds.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.biome.Biome;
import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;

public class PlainsAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(PlayerEntity player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_PLAINS_DAY_LONG;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return (biomeCategory == Biome.Category.PLAINS
                || biomeCategory == Biome.Category.MUSHROOM
                || biomeCategory == Biome.Category.RIVER)
                && isOutside()
                && PlayerStateClient.INSTANCE.isDaytime;
        }
    }

    public static class Night extends BaseAmbientSounds implements IBiomeAmbience {
        public Night(PlayerEntity player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_PLAINS_NIGHT_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_PLAINS_NIGHT_SHORT;
        }

        @Override
        public int getShortSoundDelay() {
            return world.random.nextInt(500) + 200;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return (biomeCategory == Biome.Category.PLAINS
                || biomeCategory == Biome.Category.MUSHROOM
                || biomeCategory == Biome.Category.RIVER)
                && isOutside()
                && !PlayerStateClient.INSTANCE.isDaytime;
        }
    }
}
