package svenhjol.charmonium.module;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import svenhjol.charmonium.client.AmbientSoundClient;
import svenhjol.meson.MesonModule;
import svenhjol.meson.event.AddEntityCallback;
import svenhjol.meson.event.PlayerTickCallback;
import svenhjol.meson.iface.Config;
import svenhjol.meson.iface.Module;

@Module(description = "Ambient sounds play according to the biome, time of day and depth below surface.")
public class Sounds extends MesonModule {

    @Config(name = "Volume multiplier", description = "Volume of ambient sounds is multiplied by this amount.")
    public static double volumeMultiplier = 1.0D;

    public static AmbientSoundClient client;

    @Override
    public void clientInit() {
        client = new AmbientSoundClient();

        AddEntityCallback.EVENT.register((entity -> {
            if (entity instanceof PlayerEntity
                && entity.world.isClient) {
                client.playerJoined((PlayerEntity) entity);
            }
            return ActionResult.PASS;
        }));

        PlayerTickCallback.EVENT.register((playerEntity -> {
            if (client != null && client.handler != null) {
                client.handler.tick();
            }
        }));
    }
}
