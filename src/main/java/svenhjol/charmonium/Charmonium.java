package svenhjol.charmonium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import svenhjol.charmonium.handler.LogHandler;
import svenhjol.charmonium.init.CharmoniumLoader;

public class Charmonium implements ClientModInitializer {
    public static final String MOD_ID = "charmonium";
    public static final LogHandler LOG = new LogHandler("Charmonium");
    public static CharmoniumLoader loader;

    @Override
    public void onInitializeClient() {
        loader = new CharmoniumLoader(MOD_ID);

        initBuiltInResourcePack();
    }

    private void initBuiltInResourcePack() {
        ResourceLocation packId = new ResourceLocation(MOD_ID, "journeyman");
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container
            -> ResourceManagerHelper.registerBuiltinResourcePack(packId, container, ResourcePackActivationType.DEFAULT_ENABLED));
    }

    public static boolean isEnabled(String module) {
        return CharmoniumLoader.isEnabled(module);
    }
}
