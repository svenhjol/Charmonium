package svenhjol.charmonium.module.sounds.ambience;

import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.biome.Biome;
import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;

public class OceanAmbientSounds extends BaseAmbientSounds implements IBiomeAmbience {
    public OceanAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_OCEAN_LONG;
    }

    @Override
    public boolean validBiomeConditions(Biome.Category biomeCategory) {
        return biomeCategory == Biome.Category.OCEAN
            && isOutside();
    }
}
