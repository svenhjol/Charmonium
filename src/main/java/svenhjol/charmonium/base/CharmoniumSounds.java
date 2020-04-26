package svenhjol.charmonium.base;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import svenhjol.charmonium.Charmonium;
import svenhjol.meson.MesonInstance;
import svenhjol.meson.handler.RegistryHandler;

import java.util.ArrayList;
import java.util.List;

public class CharmoniumSounds {
    public static List<SoundEvent> soundsToRegister = new ArrayList<>();

    // music
    public static final SoundEvent MUSIC_THARNA = createSound("music.tharna");
    public static final SoundEvent MUSIC_STEINN = createSound("music.steinn");
    public static final SoundEvent MUSIC_MUS = createSound("music.mus");
    public static final SoundEvent MUSIC_UNDIR = createSound("music.undir");
    public static final SoundEvent MUSIC_DISC = createSound("music_disc.charmonium");

    // ambience
    public static final SoundEvent AMBIENCE_BEACH_LONG = createSound("ambience.beach.long");
    public static final SoundEvent AMBIENCE_CAVE_LONG = createSound("ambience.cave.long");
    public static final SoundEvent AMBIENCE_CAVE_SHORT = createSound("ambience.cave.short");
    public static final SoundEvent AMBIENCE_CRYSTALS_SHORT = createSound("ambience.crystals.short");
    public static final SoundEvent AMBIENCE_DEEP_LONG = createSound("ambience.deep.long");
    public static final SoundEvent AMBIENCE_DEEP_SHORT = createSound("ambience.deep.short");
    public static final SoundEvent AMBIENCE_DESERT_DAY_LONG = createSound("ambience.desert.day_long");
    public static final SoundEvent AMBIENCE_DESERT_DAY_SHORT = createSound("ambience.desert.day_short");
    public static final SoundEvent AMBIENCE_DESERT_NIGHT_LONG = createSound("ambience.desert.night_long");
    public static final SoundEvent AMBIENCE_DESERT_NIGHT_SHORT = createSound("ambience.desert.night_short");
    public static final SoundEvent AMBIENCE_END_LONG = createSound("ambience.end.long");
    public static final SoundEvent AMBIENCE_EXTREME_HILLS_DAY_LONG = createSound("ambience.extreme_hills.day_long");
    public static final SoundEvent AMBIENCE_EXTREME_HILLS_DAY_SHORT = createSound("ambience.extreme_hills.day_short");
    public static final SoundEvent AMBIENCE_EXTREME_HILLS_NIGHT_LONG = createSound("ambience.extreme_hills.night_long");
    public static final SoundEvent AMBIENCE_FOREST_DAY_LONG = createSound("ambience.forest.day_long");
    public static final SoundEvent AMBIENCE_FOREST_NIGHT_LONG = createSound("ambience.forest.night_long");
    public static final SoundEvent AMBIENCE_ICY_DAY_LONG = createSound("ambience.icy.day_long");
    public static final SoundEvent AMBIENCE_ICY_DAY_SHORT = createSound("ambience.icy.day_short");
    public static final SoundEvent AMBIENCE_ICY_NIGHT_LONG = createSound("ambience.icy.night_long");
    public static final SoundEvent AMBIENCE_JUNGLE_DAY_LONG = createSound("ambience.jungle.day_long");
    public static final SoundEvent AMBIENCE_JUNGLE_NIGHT_LONG = createSound("ambience.jungle.night_long");
    public static final SoundEvent AMBIENCE_MINESHAFT_SHORT = createSound("ambience.mineshaft.short");
    public static final SoundEvent AMBIENCE_NETHER_LONG = createSound("ambience.nether.long");
    public static final SoundEvent AMBIENCE_NETHER_SHORT = createSound("ambience.nether.short");
    public static final SoundEvent AMBIENCE_OCEAN_LONG = createSound("ambience.ocean.long");
    public static final SoundEvent AMBIENCE_PLAINS_DAY_LONG = createSound("ambience.plains.day_long");
    public static final SoundEvent AMBIENCE_PLAINS_NIGHT_LONG = createSound("ambience.plains.night_long");
    public static final SoundEvent AMBIENCE_PLAINS_NIGHT_SHORT = createSound("ambience.plains.night_short");
    public static final SoundEvent AMBIENCE_SAVANNA_DAY_LONG = createSound("ambience.savanna.day_long");
    public static final SoundEvent AMBIENCE_SAVANNA_NIGHT_LONG = createSound("ambience.savanna.night_long");
    public static final SoundEvent AMBIENCE_SWAMP_DAY_LONG = createSound("ambience.swamp.day_long");
    public static final SoundEvent AMBIENCE_SWAMP_NIGHT_LONG = createSound("ambience.swamp.night_long");
    public static final SoundEvent AMBIENCE_TAIGA_DAY_LONG = createSound("ambience.taiga.day_long");
    public static final SoundEvent AMBIENCE_TAIGA_NIGHT_LONG = createSound("ambience.taiga.night_long");
    public static final SoundEvent AMBIENCE_VILLAGE_SHORT = createSound("ambience.village.short");
    public static final SoundEvent AMBIENCE_HIGH = createSound("ambience.high");

    public static SoundEvent createSound(String name) {
        ResourceLocation res = new ResourceLocation(Charmonium.MOD_ID, name);
        SoundEvent sound = new SoundEvent(res).setRegistryName(res);
        soundsToRegister.add(sound);
        return sound;
    }

    public static void init(MesonInstance instance) {
        soundsToRegister.forEach(RegistryHandler::registerSound);
        instance.log.debug("Registered sounds");
    }
}
