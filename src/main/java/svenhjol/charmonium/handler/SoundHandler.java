package svenhjol.charmonium.handler;

import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.iface.IAmbientSound;

import java.util.ArrayList;
import java.util.List;

public class SoundHandler<T extends IAmbientSound> {
    private Player player;
    private final List<T> sounds = new ArrayList<>();

    public SoundHandler(@NotNull Player player) {
        updatePlayer(player);
    }

    public void updatePlayer(@NotNull Player player) {
        this.player = player;
        sounds.forEach(s -> s.updatePlayer(this.player));
    }

    public List<T> getSounds() {
        return sounds;
    }

    public Player getPlayer() {
        return player;
    }

    public void tick() {
        if (player.level == null) return;
        sounds.forEach(T::tick);
    }

    public void stop() {
        sounds.forEach(T::stop);
    }
}
