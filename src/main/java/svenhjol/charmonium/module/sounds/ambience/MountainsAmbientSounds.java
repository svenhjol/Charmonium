package svenhjol.charmonium.module.sounds.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.biome.Biome;
import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;

public class MountainsAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(PlayerEntity player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_MOUNTAINS_DAY_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_MOUNTAINS_DAY_SHORT;
        }

        @Override
        public float getShortSoundVolume() {
            return super.getShortSoundVolume() - 0.15F;
        }

        @Override
        public int getShortSoundDelay() {
            return world.random.nextInt(600) + 600;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return biomeCategory == Biome.Category.EXTREME_HILLS
                && PlayerStateClient.INSTANCE.isDaytime
                && isOutside();
        }
    }

    public static class Night extends BaseAmbientSounds implements IBiomeAmbience {
        public Night(PlayerEntity player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_MOUNTAINS_NIGHT_LONG;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return biomeCategory == Biome.Category.EXTREME_HILLS
                && isOutside()
                && !PlayerStateClient.INSTANCE.isDaytime;
        }
    }
}
