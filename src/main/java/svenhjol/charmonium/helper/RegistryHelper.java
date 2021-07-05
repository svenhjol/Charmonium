package svenhjol.charmonium.helper;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import svenhjol.charmonium.Charmonium;

/**
 * @version 1.0.0-charmonium
 */
public class RegistryHelper {
    public static SoundEvent sound(String id) {
        ResourceLocation res = new ResourceLocation(Charmonium.MOD_ID, id);
        LogHelper.debug(RegistryHelper.class, "Registering sound `" + res + "`");
        return Registry.register(Registry.SOUND_EVENT, res, new SoundEvent(res));
    }
}
