package svenhjol.charmonium.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.level.Level;

public interface AddUndergroundAmbienceCallback {
    Event<AddUndergroundAmbienceCallback> EVENT = EventFactory.createArrayBacked(AddUndergroundAmbienceCallback.class, listeners -> level -> {
        for (AddUndergroundAmbienceCallback listener : listeners) {
            if (listener.interact(level)) {
                return true;
            }
        }
        return false;
    });

    boolean interact(Level level);
}
