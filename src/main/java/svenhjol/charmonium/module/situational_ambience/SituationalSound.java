package svenhjol.charmonium.module.situational_ambience;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.iface.IAmbientSound;

public abstract class SituationalSound implements IAmbientSound {
    protected Minecraft client;
    protected Player player;
    protected ClientLevel level;
    protected boolean isValid;
    protected boolean playUnderWater = false;

    public SituationalSound(Player player) {
        this.client = Minecraft.getInstance();
        this.player = player;
        this.level = (ClientLevel) player.level;
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
        this.level = (ClientLevel) player.level;
    }

    public abstract boolean isValidSituationCondition();

    @Override
    public boolean isValid() {

        // Initial filters.
        if (client.level == null || level == null) return false;
        if (!player.isAlive()) return false;
        if (player.isUnderWater() && !playUnderWater) return false;

        return isValidSituationCondition()
            && isValidPlayerCondition();
    }

    @Override
    public float getVolumeScaling() {
        return SituationalAmbience.volumeScaling;
    }
}
