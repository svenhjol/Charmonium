package svenhjol.charmonium.helper;

import net.fabricmc.loader.api.FabricLoader;

/**
 * @version 1.0.0-charmonium
 */
public class DebugHelper {
    private static final boolean isDevelopmentEnvironment = FabricLoader.getInstance().isDevelopmentEnvironment();

    public static boolean isDebugMode() {
        return isDevelopmentEnvironment;
    }
}
