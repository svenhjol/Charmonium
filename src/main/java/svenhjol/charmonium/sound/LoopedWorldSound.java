package svenhjol.charmonium.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.CharmoniumClient;

import java.util.ConcurrentModificationException;

public abstract class LoopedWorldSound extends WorldSound {
    protected LoopingSound soundInstance;

    public LoopedWorldSound(Player player) {
        super(player);
    }

    public AbstractTickableSoundInstance getSoundInstance() {
        return soundInstance;
    }

    @Override
    public void tick() {
        var log = CharmoniumClient.instance().log();
        boolean nowValid = isValid();

        if (isValid && !nowValid) isValid = false;
        if (!isValid && nowValid) isValid = true;

        if (isValid && !isPlaying()) {
            soundInstance = new LoopingSound(player, getSound(), (float)(getVolume() * getVolumeScaling()), getPitch(), p -> isValid);
            try {
                getSoundManager().play(this.soundInstance);
            } catch (ConcurrentModificationException e) {
                log.debug(this.getClass(), "Exception in tick");
            }
        }
    }
}
