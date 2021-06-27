package svenhjol.charmonium.module.underground_ambience;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.iface.IAmbientSound;
import svenhjol.charmonium.sounds.LoopingSound;

import java.util.ConcurrentModificationException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class UndergroundSound implements IAmbientSound {
    protected boolean isValid = false;
    protected Player player;
    protected ClientLevel level;
    protected AbstractTickableSoundInstance soundInstance = null;
    protected Supplier<SoundEvent> soundCondition;
    protected Predicate<UndergroundSound> validCondition;

    protected UndergroundSound(Player player, Predicate<UndergroundSound> validCondition, Supplier<SoundEvent> soundCondition) {
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
    public SoundManager getSoundManager() {
        return Minecraft.getInstance().getSoundManager();
    }

    @Override
    public void tick() {
        boolean nowValid = isValid();

        if (isValid && !nowValid)
            isValid = false;

        if (!isValid && nowValid)
            isValid = true;

        if (isValid && !isPlayingSound()) {
            this.soundInstance = new LoopingSound(player, getSound(), getVolume(), getPitch(), p -> isValid);
            try {
                getSoundManager().play(this.soundInstance);
            } catch (ConcurrentModificationException e) {
                Charmonium.LOG.debug("Exception in tick");
            }
        }
    }

    public boolean isPlayingSound() {
        return this.soundInstance != null && !this.soundInstance.isStopped();
    }

    @Override
    public boolean isValid() {
        if (level == null)
            return false;

        return this.validCondition.test(this);
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return this.soundCondition.get();
    }
}
