package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

public class BadlandsAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_BADLANDS_DAY_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_BADLANDS_DAY_SHORT;
        }

        @Override
        public int getShortSoundDelay() {
            return world.random.nextInt(700) + 700;
        }

        @Override
        public float getShortSoundVolume() {
            return super.getShortSoundVolume() - 0.15F;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return biomeCategory == Biome.BiomeCategory.MESA
                && PlayerStateClient.INSTANCE.isDaytime
                && isOutside();
        }
    }

    public static class Night extends BaseAmbientSounds implements IBiomeAmbience {
        public Night(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_BADLANDS_NIGHT_LONG;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return biomeCategory == Biome.BiomeCategory.MESA
                && isOutside()
                && !PlayerStateClient.INSTANCE.isDaytime;
        }
    }
}
