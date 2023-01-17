package svenhjol.charmonium.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import svenhjol.charmonium.Charmonium;

@Mod(Charmonium.MOD_ID)
public class ForgeModInitializer {
    public ForgeModInitializer() {
        // Just run the client init.
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ForgeClientModInitializer::new);
    }
}
