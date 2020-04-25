package svenhjol.charmonium.ambience.client.ambience;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import svenhjol.charm.Charm;
import svenhjol.strange.Strange;
import svenhjol.charmonium.ambience.client.ambience.BaseAmbientSounds;
import svenhjol.charmonium.ambience.client.iface.IBiomeAmbience;
import svenhjol.charmonium.base.CharmoniumSounds;

import javax.annotation.Nullable;

public class ExtremeHillsAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(PlayerEntity player, SoundHandler soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_EXTREME_HILLS_DAY_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return CharmoniumSounds.AMBIENCE_EXTREME_HILLS_DAY_SHORT;
        }

        @Override
        public float getShortSoundVolume() {
            return super.getShortSoundVolume() - 0.1F;
        }

        @Override
        public int getShortSoundDelay() {
            return world.rand.nextInt(600) + 500;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return biomeCategory == Biome.Category.EXTREME_HILLS
                && Charm.client.isDaytime
                && isOutside();
        }
    }

    public static class Night extends BaseAmbientSounds implements IBiomeAmbience {
        public Night(PlayerEntity player, SoundHandler soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_EXTREME_HILLS_NIGHT_LONG;
        }

        @Nullable
        @Override
        public SoundEvent getShortSound() {
            return null;
        }

        @Override
        public boolean validBiomeConditions(Biome.Category biomeCategory) {
            return biomeCategory == Biome.Category.EXTREME_HILLS
                && isOutside()
                && !Charm.client.isDaytime;
        }
    }
}
