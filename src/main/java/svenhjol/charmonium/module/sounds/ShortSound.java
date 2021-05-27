package svenhjol.charmonium.module.sounds;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import svenhjol.charm.mixin.accessor.MovingSoundInstanceAccessor;

public class ShortSound extends MovingSoundInstance {
    private final ClientPlayerEntity player;

    public ShortSound(ClientPlayerEntity player, SoundEvent sound, float volume) {
        super(sound, SoundCategory.AMBIENT);
        this.player = player;
        this.repeat = false;
        this.repeatDelay = 0;
        this.volume = volume;
        this.looping = true;
    }

    @Override
    public void tick() {
        if (!this.player.isAlive())
            ((MovingSoundInstanceAccessor)this).setDone(true);
    }
}
