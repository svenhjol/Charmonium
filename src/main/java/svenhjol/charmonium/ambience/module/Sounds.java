package svenhjol.charmonium.ambience.module;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.ambience.client.AmbientSoundClient;
import svenhjol.charmonium.base.CharmoniumCategories;
import svenhjol.meson.MesonModule;
import svenhjol.meson.iface.Config;
import svenhjol.meson.iface.Module;

@Module(mod = Charmonium.MOD_ID, category = CharmoniumCategories.AMBIENCE, hasSubscriptions = true,
    description = "Ambient background sounds according to the biome, time of day and depth below surface.")
public class Sounds extends MesonModule {
    @Config(name = "Volume multiplier", description = "Volume of ambient sounds is multiplied by this amount.")
    public static double volumeMultiplier = 1.0D;

    @Config(name = "Biome sounds even if Environs is present", description = "By default, biome sounds of this module will be disabled if the 'Environs' mod is present.\n" +
        "Set this to true if you want to force them to be enabled.")
    public static boolean biomesWithEnvirons = false;

    @Config(name = "Feature sounds even if Environs is present", description = "By default, feature sounds of this module will be disabled if the 'Environs' mod is present.\n" +
        "Feature sounds include light-based cave sounds, crystal caves and Mineshafts.")
    public static boolean featureWithEnvirons = true;

    @OnlyIn(Dist.CLIENT)
    public static AmbientSoundClient ambientSoundClient;

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        ambientSoundClient = new AmbientSoundClient();
    }

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof PlayerEntity
            && event.getEntity().world.isRemote
        ) {
            ambientSoundClient.playerJoined((PlayerEntity) event.getEntity());
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END
            && event.player.world.isRemote
            && ambientSoundClient.handler != null
        ) {
            ambientSoundClient.handler.tick();
        }
    }
}
