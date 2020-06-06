package svenhjol.charmonium.ambience.client.ambience;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.biome.Biome;
import svenhjol.charm.Charm;
import svenhjol.charmonium.ambience.client.LongSound;

@SuppressWarnings("unused")
public abstract class BiomeAmbientSounds extends BaseAmbientSounds {
    public BiomeAmbientSounds(PlayerEntity player, SoundHandler soundHandler) {
        super(player, soundHandler);
    }

    @Override
    public void setLongSound() {
        if (isDay()) {
            this.longSound = new LongSound(player, getLongSound(), getLongSoundVolume(), p -> isDay());
        } else if (isNight()) {
            this.longSound = new LongSound(player, getLongSound(), getLongSoundVolume(), p -> isNight());
        }
    }

    public boolean isDay() {
        return isValid() && Charm.client.isDaytime;
    }

    public boolean isNight() {
        return isValid() && !Charm.client.isDaytime;
    }

    public abstract Biome.Category getBiomeCategory();
}
