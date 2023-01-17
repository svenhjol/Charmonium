 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.biome_ambience.sounds.TheEnd;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.iface.ISoundType;
 import svenhjol.charmonium.init.SoundHandler;
 import svenhjol.charmonium.sounds.RepeatedWorldSound;
 import svenhjol.charmonium.sounds.WorldSound;

 public class Alien implements ISoundType<WorldSound> {
    public static SoundEvent SOUND;

    public Alien() {
        SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("world.alien"));
    }

    public void addSounds(SoundHandler<WorldSound> handler) {
        if (!WorldAmbience.alien) return;

        handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                var holder = getBiomeHolder(player.blockPosition());
                return TheEnd.VALID_BIOME.test(holder);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return true;
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return SOUND;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(400) + 300;
            }

            @Override
            public float getVolume() {
                return 0.85F;
            }
        });
    }
}
