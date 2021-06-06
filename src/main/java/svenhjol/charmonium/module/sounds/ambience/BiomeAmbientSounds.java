package svenhjol.charmonium.module.sounds.ambience;

import net.minecraft.client.sounds.SoundManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.module.sounds.LongSound;

@SuppressWarnings("unused")
public abstract class BiomeAmbientSounds extends BaseAmbientSounds {
    public BiomeAmbientSounds(Player player, SoundManager soundHandler) {
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

    public abstract Biome.BiomeCategory getBiomeCategory();
}
