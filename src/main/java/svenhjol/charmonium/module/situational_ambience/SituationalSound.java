package svenhjol.charmonium.module.situational_ambience;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.iface.IAmbientSound;
import svenhjol.charmonium.sounds.SingleSound;

import java.util.ConcurrentModificationException;
import java.util.function.Function;
import java.util.function.Predicate;

public class SituationalSound implements IAmbientSound {
    protected int soundTicks = 0;
    protected boolean isValid;
    protected Player player;
    protected ClientLevel level;
    protected Function<SituationalSound, SoundEvent> soundCondition;
    protected Predicate<SituationalSound> validCondition;
    protected BlockPos pos;
    protected SingleSound soundInstance;

    public SituationalSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        this.player = player;
        this.level = (ClientLevel) player.level;
        this.soundCondition = soundCondition;
        this.validCondition = validCondition;
    }

    @Override
    public ClientLevel getLevel() {
        return level;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void updatePlayer(Player player) {
        this.player = player;
    }

    @Override
    public SingleSound getSoundInstance() {
        return soundInstance;
    }

    @Override
    public boolean isValid() {
        if (level == null)
            return false;

        if (!player.isAlive())
            return false;

        return validCondition.test(this);
    }

    @Override
    public void tick() {
        if (--soundTicks >= 0)
            return;

        soundTicks = getDelay();
        isValid = isValid();

        if (isValid) {
            soundInstance = new SingleSound(getPlayer(), getSound(), getVolume(), getPitch(), getPos());
            SoundManager manager = getSoundManager();

            try {
                if (!manager.isActive(soundInstance))
                    manager.play(soundInstance);
            } catch (ConcurrentModificationException e) {
                Charmonium.LOG.warn("Exception in manager.play");
            }
        }
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return soundCondition.apply(this);
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
