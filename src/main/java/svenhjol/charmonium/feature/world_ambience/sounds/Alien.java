 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.sounds.SoundEvent;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.charmony.feature.FeatureResolver;
 import svenhjol.charmonium.feature.biome_ambience.sounds.TheEnd;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.SoundType;
 import svenhjol.charmonium.feature.world_ambience.client.RepeatedWorldSound;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.feature.world_ambience.client.WorldSound;

 public class Alien implements SoundType<WorldSound>, FeatureResolver<WorldAmbience> {
    public final SoundEvent sound;

    public Alien() {
        sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(Charmonium.ID, "world.alien"));
    }

    public void addSounds(SoundHandler<WorldSound> handler) {
        if (!feature().alien()) return;

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
                return sound;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(400) + 300;
            }

            @Override
            public float getVolume() {
                return 0.85f;
            }
        });
    }

     @Override
     public Class<WorldAmbience> typeForFeature() {
         return WorldAmbience.class;
     }
 }
