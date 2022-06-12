package svenhjol.charmonium.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.level.Level;

/**
 * @version 4.0.0-charmonium
 */
public interface AddCaveAmbienceCheck {
    Event<AddCaveAmbienceCheck> EVENT = EventFactory.createArrayBacked(AddCaveAmbienceCheck.class, listeners -> level -> {
        for (AddCaveAmbienceCheck listener : listeners) {
            if (listener.interact(level)) {
                return true;
            }
        }
        return false;
    });

    boolean interact(Level level);
}
