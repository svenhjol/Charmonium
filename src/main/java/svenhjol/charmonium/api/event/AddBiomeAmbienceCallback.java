package svenhjol.charmonium.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.level.Level;

public interface AddBiomeAmbienceCallback {
    Event<AddBiomeAmbienceCallback> EVENT = EventFactory.createArrayBacked(AddBiomeAmbienceCallback.class, listeners -> level -> {
        for (AddBiomeAmbienceCallback listener : listeners) {
            if (listener.interact(level)) {
                return true;
            }
        }
        return false;
    });

    boolean interact(Level level);
}
