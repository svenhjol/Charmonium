package svenhjol.charmonium.helper;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import svenhjol.charmonium.Charmonium;

/**
 * @version charmonium-1
 */
public class RegistryHelper {
    public static SoundEvent sound(String id) {
        ResourceLocation res = new ResourceLocation(Charmonium.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, res, new SoundEvent(res));
    }
}
