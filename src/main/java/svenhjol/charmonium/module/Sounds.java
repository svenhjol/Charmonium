package svenhjol.charmonium.module;

import net.minecraft.util.Identifier;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Config;
import svenhjol.charm.base.iface.Module;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.client.SoundsClient;

import java.util.ArrayList;
import java.util.List;

@Module(mod = Charmonium.MOD_ID, client = SoundsClient.class, description = "Ambient sounds play according to the biome, time of day and depth below surface.")
public class Sounds extends CharmModule {
    public static List<Identifier> outdoorDimensions = new ArrayList<>();

    @Config(name = "Volume multiplier", description = "Volume of ambient sounds is multiplied by this amount.")
    public static double volumeMultiplier = 1.0D;

    @Config(name = "Outdoor dimensions", description = "Dimensions that have outdoor ambience. Overworld is included by default.")
    public static List<String> configOutdoorDimensions = new ArrayList<>();

    @Config(name = "Cave ambience", description = "If true, plays ambient sounds when the player is less than sea-level and light level is less than 11.")
    public static boolean caveAmbience = true;

    @Config(name = "Deep ambience", description = "If true, plays ambient sounds when the player is lower than Y 32 and light level is less than 10.")
    public static boolean deepAmbience = true;

    @Config(name = "High ambience", description = "If true, plays ambience when the player is higher than Y 150.")
    public static boolean highAmbience = true;

    @Config(name = "Mineshaft ambience", description = "If true, plays ambient sounds when the player is inside a mineshaft.")
    public static boolean mineshaftAmbience = true;

    @Config(name = "Village ambience", description = "If true, plays ambience when the player is within a village.")
    public static boolean villageAmbience = true;

    @Config(name = "End ambience", description = "If true, plays ambience when the player is in the End dimension.")
    public static boolean endAmbience = true;

    @Config(name = "Badlands ambience", description = "If true, plays ambience when the player is in a badlands biome.")
    public static boolean badlandsAmbience = true;

    @Config(name = "Beach ambience", description = "If true, plays ambience when the player is in a beach biome.")
    public static boolean beachAmbience = true;

    @Config(name = "Desert ambience", description = "If true, plays ambience when the player is in a desert biome.")
    public static boolean desertAmbience = true;

    @Config(name = "Forest ambience", description = "If true, plays ambience when the player is in a forest biome.")
    public static boolean forestAmbience = true;

    @Config(name = "Icy ambience", description = "If true, plays ambience when the player is in an icy biome.")
    public static boolean icyAmbience = true;

    @Config(name = "Jungle ambience", description = "If true, plays ambience when the player is in a jungle biome.")
    public static boolean jungleAmbience = true;

    @Config(name = "Ocean ambience", description = "If true, plays ambience when the player is an ocean biome but not underwater.")
    public static boolean oceanAmbience = true;

    @Config(name = "Mountains ambience", description = "If true, plays ambience when the player is in a mountains biome.")
    public static boolean mountainsAmbience = true;

    @Config(name = "Plains ambience", description = "If true, plays ambience when the player is in a plains biome.")
    public static boolean plainsAmbience = true;

    @Config(name = "Savanna ambience", description = "If true, plays ambience when the player is in a savanna biome.")
    public static boolean savannaAmbience = true;

    @Config(name = "Swamp ambience", description = "If true, plays ambience when the player is in a swamp biome.")
    public static boolean swampAmbience = true;

    @Config(name = "Taiga ambience", description = "If true, plays ambience when the player is in a taiga biome.")
    public static boolean taigaAmbience = true;

    @Override
    public void register() {
        if (configOutdoorDimensions.size() > 0)
            configOutdoorDimensions.forEach(id -> outdoorDimensions.add(new Identifier(id)));
    }
}
