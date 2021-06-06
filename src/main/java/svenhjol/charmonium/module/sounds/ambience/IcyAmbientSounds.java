package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.handler.ModuleHandler;
import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charm.module.snow_storms.SnowStorms;
import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

public class IcyAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_ICY_DAY_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_ICY_DAY_SHORT;
        }

        @Override
        public int getShortSoundDelay() {
            return world.random.nextInt(400) + 500;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return biomeCategory == Biome.BiomeCategory.ICY
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
            return CharmoniumSounds.AMBIENCE_ICY_NIGHT_LONG;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return biomeCategory == Biome.BiomeCategory.ICY
                && isOutside()
                && !PlayerStateClient.INSTANCE.isDaytime;
        }
    }

    public static class Thunderstorm extends BaseAmbientSounds implements IBiomeAmbience {
        public Thunderstorm(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_ICY_THUNDERSTORM;
        }

        @Override
        public float getLongSoundVolume() {
            return super.getLongSoundVolume() + 0.25F;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return biomeCategory == Biome.BiomeCategory.ICY
                && isOutside()
                && getWorld().isThundering()
                && ModuleHandler.enabled(SnowStorms.class);
        }
    }
}
