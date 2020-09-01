package svenhjol.charmonium.client.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.biome.Biome;
import svenhjol.charm.module.PlayerState;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.charmonium.iface.IBiomeAmbience;

import javax.annotation.Nullable;

public class SwampAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(PlayerEntity player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_SWAMP_DAY_LONG;
        }

        @Override
        public float getLongSoundVolume() {
            return super.getLongSoundVolume() - 0.2F;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return biomeCategory == Biome.Category.SWAMP
                && isOutside()
                && PlayerState.client.isDaytime;
        }
    }

    public static class Night extends BaseAmbientSounds implements IBiomeAmbience {
        public Night(PlayerEntity player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_SWAMP_NIGHT_LONG;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return biomeCategory == Biome.Category.SWAMP
                && isOutside()
                && !PlayerState.client.isDaytime;
        }
    }
}
