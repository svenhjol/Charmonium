package svenhjol.charmonium.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmony.base.Mods;

import java.util.ConcurrentModificationException;

public abstract class RepeatedWorldSound extends WorldSound {
    protected SingleSound soundInstance;
    protected BlockPos pos;
    protected int soundTicks = 100; // set something high here so it doesn't autoplay when player logs in

    public RepeatedWorldSound(Player player) {
        super(player);
    }

    @Override
    public AbstractTickableSoundInstance getSoundInstance() {
        return soundInstance;
    }

    @Override
    public void tick() {
        var log = Mods.client(Charmonium.ID).log();

        if (--soundTicks >= 0)
            return;

        soundTicks = getDelay();
        isValid = isValid();

        if (isValid) {
            soundInstance = new SingleSound(getPlayer(), getSound(), (float)(getVolume() * getVolumeScaling()), getPitch(), getPos());
            var manager = getSoundManager();

            try {
                if (!manager.isActive(soundInstance)) {
                    manager.play(soundInstance);
                }
            } catch (ConcurrentModificationException e) {
                log.debug(this.getClass(), "Exception in manager.play");
            }
        }
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(200) + 200;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    @Nullable
    public BlockPos getPos() {
        return pos;
    }

}
