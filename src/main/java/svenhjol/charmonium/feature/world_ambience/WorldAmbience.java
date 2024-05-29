package svenhjol.charmonium.feature.world_ambience;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import svenhjol.charmonium.charmony.Resolve;
import svenhjol.charmonium.charmony.annotation.Configurable;
import svenhjol.charmonium.charmony.annotation.Feature;
import svenhjol.charmonium.charmony.client.ClientFeature;
import svenhjol.charmonium.charmony.client.ClientLoader;
import svenhjol.charmonium.feature.world_ambience.client.Handlers;
import svenhjol.charmonium.feature.world_ambience.client.Registers;

import java.util.List;

@Feature(description = "Plays ambient sound according to features of the world around the player.")
public final class WorldAmbience extends ClientFeature {
    public final Registers registers;
    public final Handlers handlers;

    @Configurable(name = "Volume scaling", description = "Affects the volume of all situational ambient sounds. 1.0 is full volume.", requireRestart = false)
    private static double volumeScaling = 0.55d;

    @Configurable(
        name = "Above ground for ambience silencing",
        description = """
            Number of blocks above the ground that biome ambience will be silenced.
            Set to zero to disable.""",
        requireRestart = false
    )
    private static int cullSoundAboveGround = 32;

    @Configurable(name = "Alien", description = "If true, plays ambient sounds while anywhere in the End.", requireRestart = false)
    private static boolean alien = true;

    @Configurable(name = "Bleak", description = "If true, plays ambient sounds in cold and/or barren overworld environments.", requireRestart = false)
    private static boolean bleak = true;

    @Configurable(name = "Cave depth", description = "If true, plays more intense cave sounds when below Y 0 and light level is lower than the cave light level.", requireRestart = false)
    private static boolean caveDepth = true;

    @Configurable(name = "Cave drone", description = "If true, plays a low drone sound when in a cave below the cave drone cutoff.", requireRestart = false)
    private static boolean caveDrone = true;

    @Configurable(name = "Cave drone cutoff", description = "Height at which the cave drone will be silenced.", requireRestart = false)
    private static int caveDroneCutoff = 48;

    @Configurable(name = "Cave light level", description = "Light level at which cave ambience will be dampened.", requireRestart = false)
    private static int caveLightLevel = 10;

    @Configurable(name = "Deepslate", description = "If true, plays ambient sounds when the player is underground and near deepslate blocks.", requireRestart = false)
    private static boolean deepslate = true;

    @Configurable(name = "Dry", description = "If true, plays ambient sounds in dry and/or hot overworld environments.", requireRestart = false)
    private static boolean dry = true;

    @Configurable(name = "Geode", description = "If true, plays ambient sounds from a nearby amethyst geode.", requireRestart = false)
    private static boolean geode = true;

    @Configurable(name = "Gravel", description = "If true, plays ambient sounds when the player is underground and near gravel blocks.", requireRestart = false)
    private static boolean gravel = true;

    @Configurable(name = "High", description = "If true, plays ambient sounds when high up in the overworld.", requireRestart = false)
    private static boolean high = true;

    @Configurable(name = "Mansion", description = "If true, plays ambient sounds while inside a woodland mansion.", requireRestart = false)
    private static boolean mansion = true;

    @Configurable(name = "Mineshaft", description = "If true, plays ambient sounds from a nearby mineshaft.", requireRestart = false)
    private static boolean mineshaft = true;

    @Configurable(name = "Night plains", description = "If true, plays ambient sounds in plains environments at night.", requireRestart = false)
    private static boolean nightPlains = true;

    @Configurable(name = "Snowstorm", description = "If true, plays ambient sounds when in a cold biome during a thunderstorm.", requireRestart = false)
    private static boolean snowstorm = true;

    @Configurable(name = "Underground water", description = "If true, plays water sounds from a nearby water source when underground.", requireRestart = false)
    private static boolean undergroundWater = true;

    @Configurable(name = "Village", description = "If true, plays ambient sounds when a player is inside a village.", requireRestart = false)
    private static boolean village = true;

    @Configurable(name = "Valid cave ambience dimensions", description = "Dimensions in which cave ambience (drone and depth) will be played.", requireRestart = false)
    private static List<String> caveDimensions = List.of(
        "minecraft:overworld"
    );

    public WorldAmbience(ClientLoader loader) {
        super(loader);

        registers = new Registers(this);
        handlers = new Handlers(this);
    }

    @Override
    public boolean canBeDisabled() {
        return true;
    }

    public List<String> caveDimensions() {
        return caveDimensions;
    }

    public List<ResourceLocation> validCaveDimensions() {
        return registers.validCaveDimensions;
    }

    public double volumeScaling() {
        return Mth.clamp(volumeScaling, 0.0d, 1.0d);
    }

    public int cullSoundAboveGround() {
        return Mth.clamp(cullSoundAboveGround, 0, 1000);
    }

    public int caveLightLevel() {
        return Mth.clamp(caveLightLevel, 0, 15);
    }

    public int caveDroneCutoff() {
        return Mth.clamp(caveDroneCutoff, -64, 1000);
    }

    public boolean alien() {
        return alien;
    }

    public boolean bleak() {
        return bleak;
    }

    public boolean caveDepth() {
        return caveDepth;
    }

    public boolean caveDrone() {
        return caveDrone;
    }

    public boolean deepslate() {
        return deepslate;
    }

    public boolean dry() {
        return dry;
    }

    public boolean geode() {
        return geode;
    }

    public boolean gravel() {
        return gravel;
    }

    public boolean high() {
        return high;
    }

    public boolean mansion() {
        return mansion;
    }

    public boolean mineshaft() {
        return mineshaft;
    }

    public boolean nightPlains() {
        return nightPlains;
    }

    public boolean snowstorm() {
        return snowstorm;
    }

    public boolean undergroundWater() {
        return undergroundWater;
    }

    public boolean village() {
        return village;
    }

    /**
     * Can be called by other mods to add cave ambience to a custom dimension at runtime.
     */
    @SuppressWarnings("unused")
    public static void addCaveAmbienceToDimension(Level level) {
        var dimension = level.dimension().location();
        Resolve.feature(WorldAmbience.class).registers.validCaveDimensions.add(dimension);
    }
}
