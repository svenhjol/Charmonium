package svenhjol.charmonium.feature.biome_ambience;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import svenhjol.charmonium.charmony.annotation.Configurable;
import svenhjol.charmonium.charmony.annotation.Feature;
import svenhjol.charmonium.charmony.client.ClientFeature;
import svenhjol.charmonium.charmony.client.ClientLoader;
import svenhjol.charmonium.feature.biome_ambience.client.Handlers;
import svenhjol.charmonium.feature.biome_ambience.client.Registers;

import java.util.Arrays;
import java.util.List;

@Feature(description = "Plays ambient background sound according to the biome and time of day.")
public final class BiomeAmbience extends ClientFeature {
    public final Registers registers;
    public final Handlers handlers;

    @Configurable(name = "Above ground for ambience silencing", description = "Number of blocks above the ground that biome ambience will be silenced.\n" +
        "Set to zero to disable.", requireRestart = false)
    private static int cullSoundAboveGround = 32;

    @Configurable(name = "Biome sound blending", description = "Number of blocks to check for neighbouring biomes.\n" +
        "Set to zero to disable.", requireRestart = false)
    private static int biomeBlend = 32;

    @Configurable(name = "Volume scaling", description = "Affects the volume of all biome ambient sounds. 1.0 is full volume.", requireRestart = false)
    private static double volumeScaling = 0.55d;

    @Configurable(name = "Valid dimensions", description = "Dimensions in which biome ambience will be played.")
    private static List<String> dimensions = Arrays.asList(
        "minecraft:overworld",
        "minecraft:the_end"
    );

    public BiomeAmbience(ClientLoader loader) {
        super(loader);

        registers = new Registers(this);
        handlers = new Handlers(this);
    }

    @Override
    public boolean canBeDisabled() {
        return true;
    }

    public int cullSoundAboveGround() {
        return Mth.clamp(cullSoundAboveGround, 0, 1000);
    }

    public int biomeBlend() {
        return Mth.clamp(biomeBlend, 0, 64);
    }

    public double volumeScaling() {
        return Mth.clamp(volumeScaling, 0.0d, 1.0d);
    }

    public List<String> dimensions() {
        return dimensions;
    }

    public List<ResourceLocation> validDimensions() {
        return registers.validDimensions;
    }
}
