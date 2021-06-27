package svenhjol.charmonium.sounds;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.mixin.accessor.AbstractTickableSoundInstanceAccessor;

public class SingleSound extends AbstractTickableSoundInstance {
    private final Player player;

    public SingleSound(Player player, SoundEvent sound, float volume) {
        this(player, sound, volume, 1.0F);
    }

    public SingleSound(Player player, SoundEvent sound, float volume, float pitch) {
        super(sound, SoundSource.AMBIENT);
        this.player = player;
        this.looping = false;
        this.delay = 0;
        this.volume = volume;
        this.pitch = pitch;
        this.relative = true;
    }

    @Override
    public void tick() {
        if (!this.player.isAlive())
            ((AbstractTickableSoundInstanceAccessor)this).setStopped(true);
    }
}
