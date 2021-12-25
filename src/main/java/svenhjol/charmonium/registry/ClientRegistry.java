package svenhjol.charmonium.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.helper.LogHelper;

/**
 * @version 1.0.0-charmonium
 */
public class ClientRegistry {
    public static SoundEvent sound(String id) {
        ResourceLocation res = new ResourceLocation(Charmonium.MOD_ID, id);
        LogHelper.debug(ClientRegistry.class, "Registering sound `" + res + "`");
        return Registry.register(Registry.SOUND_EVENT, res, new SoundEvent(res));
    }
}
