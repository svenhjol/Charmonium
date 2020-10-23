package svenhjol.charmonium.module;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import svenhjol.charm.base.CharmModule;
import svenhjol.charm.base.iface.Config;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.client.AmbientSoundClient;
import svenhjol.charm.event.AddEntityCallback;
import svenhjol.charm.event.PlayerTickCallback;
import svenhjol.charm.base.iface.Module;

@Module(mod = Charmonium.MOD_ID, description = "Ambient sounds play according to the biome, time of day and depth below surface.")
public class Sounds extends CharmModule {

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
