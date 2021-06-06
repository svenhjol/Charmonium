package svenhjol.charmonium.init;

import svenhjol.charm.helper.RegistryHelper;
import svenhjol.charmonium.Charmonium;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class CharmoniumSounds {
    private static final Map<ResourceLocation, SoundEvent> REGISTER = new HashMap<>();

    // music
    public static final SoundEvent MUSIC_THARNA = createSound("music.tharna");
    public static final SoundEvent MUSIC_MUS = createSound("music.mus");
    public static final SoundEvent MUSIC_UNDIR = createSound("music.undir");
    public static final SoundEvent MUSIC_RUIN = createSound("music.ruin");
    public static final SoundEvent MUSIC_DISC = createSound("music_disc.charmonium");

    // ambience
    public static final SoundEvent AMBIENCE_BADLANDS_DAY_LONG = createSound("ambience.badlands.day_long");
    public static final SoundEvent AMBIENCE_BADLANDS_DAY_SHORT = createSound("ambience.badlands.day_short");
    public static final SoundEvent AMBIENCE_BADLANDS_NIGHT_LONG = createSound("ambience.badlands.night_long");
    public static final SoundEvent AMBIENCE_BEACH_LONG = createSound("ambience.beach.long");
    public static final SoundEvent AMBIENCE_CAVE_LONG = createSound("ambience.cave.long");
    public static final SoundEvent AMBIENCE_CAVE_SHORT = createSound("ambience.cave.short");
    public static final SoundEvent AMBIENCE_DEEP_LONG = createSound("ambience.deep.long");
    public static final SoundEvent AMBIENCE_DEEP_SHORT = createSound("ambience.deep.short");
    public static final SoundEvent AMBIENCE_DESERT_DAY_LONG = createSound("ambience.desert.day_long");
    public static final SoundEvent AMBIENCE_DESERT_DAY_SHORT = createSound("ambience.desert.day_short");
    public static final SoundEvent AMBIENCE_DESERT_NIGHT_LONG = createSound("ambience.desert.night_long");
    public static final SoundEvent AMBIENCE_DESERT_NIGHT_SHORT = createSound("ambience.desert.night_short");
    public static final SoundEvent AMBIENCE_END_SHORT = createSound("ambience.end.short");
    public static final SoundEvent AMBIENCE_END_LONG = createSound("ambience.end.long");
    public static final SoundEvent AMBIENCE_MOUNTAINS_DAY_LONG = createSound("ambience.mountains.day_long");
    public static final SoundEvent AMBIENCE_MOUNTAINS_DAY_SHORT = createSound("ambience.mountains.day_short");
    public static final SoundEvent AMBIENCE_MOUNTAINS_NIGHT_LONG = createSound("ambience.mountains.night_long");
    public static final SoundEvent AMBIENCE_FOREST_DAY_LONG = createSound("ambience.forest.day_long");
    public static final SoundEvent AMBIENCE_FOREST_NIGHT_LONG = createSound("ambience.forest.night_long");
    public static final SoundEvent AMBIENCE_ICY_DAY_LONG = createSound("ambience.icy.day_long");
    public static final SoundEvent AMBIENCE_ICY_DAY_SHORT = createSound("ambience.icy.day_short");
    public static final SoundEvent AMBIENCE_ICY_NIGHT_LONG = createSound("ambience.icy.night_long");
    public static final SoundEvent AMBIENCE_ICY_THUNDERSTORM = createSound("ambience.icy.thunderstorm");
    public static final SoundEvent AMBIENCE_JUNGLE_DAY_LONG = createSound("ambience.jungle.day_long");
    public static final SoundEvent AMBIENCE_JUNGLE_NIGHT_LONG = createSound("ambience.jungle.night_long");
    public static final SoundEvent AMBIENCE_MINESHAFT_SHORT = createSound("ambience.mineshaft.short");
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
        ResourceLocation id = new ResourceLocation(Charmonium.MOD_ID, name);
        SoundEvent sound = new SoundEvent(id);
        REGISTER.put(id, sound);
        return sound;
    }

    public static void init() {
        REGISTER.forEach(RegistryHelper::sound);
    }
}
