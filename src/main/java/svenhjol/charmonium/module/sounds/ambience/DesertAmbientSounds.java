package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

public class DesertAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_DESERT_DAY_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_DESERT_DAY_SHORT;
        }

        @Override
        public int getShortSoundDelay() {
            return world.random.nextInt(500) + 600;
        }

        @Override
        public float getShortSoundVolume() {
            return super.getShortSoundVolume() - 0.15F;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return (biomeCategory == Biome.BiomeCategory.DESERT
                && isOutside()
                && PlayerStateClient.INSTANCE.isDaytime);
        }
    }

    public static class Night extends BaseAmbientSounds implements IBiomeAmbience {
        public Night(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_DESERT_NIGHT_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_DESERT_NIGHT_SHORT;
        }

        @Override
        public int getShortSoundDelay() {
            return world.random.nextInt(400) + 400;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return (biomeCategory == Biome.BiomeCategory.DESERT
                && isOutside()
                && !PlayerStateClient.INSTANCE.isDaytime);
        }
    }
}
