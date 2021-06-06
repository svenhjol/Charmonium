package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

public class OceanAmbientSounds extends BaseAmbientSounds implements IBiomeAmbience {
    public OceanAmbientSounds(Player player, SoundManager soundHandler) {
        super(player, soundHandler);
    }

    @Nullable
    @Override
    public SoundEvent getLongSound() {
        return CharmoniumSounds.AMBIENCE_OCEAN_LONG;
    }

    @Override
    public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
        return biomeCategory == Biome.BiomeCategory.OCEAN
            && isOutside();
    }
}
