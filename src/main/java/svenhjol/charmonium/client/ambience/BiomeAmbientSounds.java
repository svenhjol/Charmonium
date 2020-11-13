package svenhjol.charmonium.client.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.biome.Biome;
import svenhjol.charm.client.PlayerStateClient;
import svenhjol.charmonium.client.LongSound;

@SuppressWarnings("unused")
public abstract class BiomeAmbientSounds extends BaseAmbientSounds {
    public BiomeAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
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
        return isValid() && PlayerStateClient.INSTANCE.isDaytime;
    }

    public boolean isNight() {
        return isValid() && !PlayerStateClient.INSTANCE.isDaytime;
    }

    public abstract Biome.Category getBiomeCategory();
}
