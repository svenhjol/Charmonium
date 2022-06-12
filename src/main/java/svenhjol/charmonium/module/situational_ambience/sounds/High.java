package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.situational_ambience.RepeatedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

public class High {
    public static SoundEvent HIGH;

    public static void register() {
        HIGH = ClientRegistry.sound("situational.high");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new RepeatedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                int top = level.getMaxBuildHeight() > 256 ? 200 : 150;

                return DimensionHelper.isOverworld(level)
                    && player.blockPosition().getY() > top;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return DimensionHelper.isOverworld(level)
                    && WorldHelper.isOutside(player);
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return HIGH;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(100) + 100;
            }

            @Override
            public float getVolume() {
                return 0.85F;
            }
        });
    }
}