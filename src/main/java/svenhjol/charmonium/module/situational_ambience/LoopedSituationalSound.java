package svenhjol.charmonium.module.situational_ambience;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.helper.LogHelper;
import svenhjol.charmonium.sounds.LoopingSound;

import java.util.ConcurrentModificationException;

public abstract class LoopedSituationalSound extends SituationalSound {
    protected LoopingSound soundInstance;

    public LoopedSituationalSound(Player player) {
        super(player);
    }

    public AbstractTickableSoundInstance getSoundInstance() {
        return soundInstance;
    }

    @Override
    public void tick() {
        boolean nowValid = isValid();

        if (isValid && !nowValid) isValid = false;
        if (!isValid && nowValid) isValid = true;

        if (isValid && !isPlaying()) {
            soundInstance = new LoopingSound(player, getSound(), getVolume() * getVolumeScaling(), getPitch(), p -> isValid);
            try {
                getSoundManager().play(this.soundInstance);
            } catch (ConcurrentModificationException e) {
                LogHelper.debug(this.getClass(), "Exception in tick");
            }
        }
    }
}
