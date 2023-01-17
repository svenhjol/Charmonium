package svenhjol.charmonium.proxy;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charm_core.proxy.Proxy;

import java.util.WeakHashMap;

public class BiomeTagHelperProxy {
    private static final String PREFIX = "svenhjol.charmonium";
    private static final WeakHashMap<String, TagKey<Biome>> CACHE = new WeakHashMap<>();

    public static TagKey<Biome> getDesert() {
        return getCached("getDesert");
    }

    public static TagKey<Biome> getForest() {
        return getCached("getForest");
    }

    public static TagKey<Biome> getIcy() {
        return getCached("getIcy");
    }

    public static TagKey<Biome> getJungle() {
        return getCached("getJungle");
    }

    public static TagKey<Biome> getMountain() {
        return getCached("getMountain");
    }

    public static TagKey<Biome> getOcean() {
        return getCached("getOcean");
    }

    public static TagKey<Biome> getPlains() {
        return getCached("getPlains");
    }

    public static TagKey<Biome> getRiver() {
        return getCached("getRiver");
    }

    public static TagKey<Biome> getSnowy() {
        return getCached("getSnowy");
    }

    public static TagKey<Biome> getSwamp() {
        return getCached("getSwamp");
    }

    private static TagKey<Biome> getCached(String method) {
        if (!CACHE.containsKey(method)) {
            TagKey<Biome> tag = Proxy.INSTANCE.method(PREFIX, "BiomeTagHelper", method);
            CACHE.put(method, tag);
        }
        return CACHE.get(method);
    }
}
