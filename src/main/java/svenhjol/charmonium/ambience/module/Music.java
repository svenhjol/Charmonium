package svenhjol.charmonium.ambience.module;

import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.NameTagItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import svenhjol.charm.tweaks.item.CharmMusicDiscItem;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.ambience.client.MusicClient;
import svenhjol.charmonium.base.CharmoniumCategories;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.meson.MesonModule;
import svenhjol.meson.iface.Module;

@Module(mod = Charmonium.MOD_ID, category = CharmoniumCategories.AMBIENCE, hasSubscriptions = true,
    description = "Custom music tracks will play in certain situations.\n" +
        "Charm's 'Ambient Music Improvements' module must be enabled for this to work.")
public class Music extends MesonModule {
    private static final String NAME = "svenhjol";
    private static CharmMusicDiscItem disc;

    @OnlyIn(Dist.CLIENT)
    public static MusicClient musicClient;

    @Override
    public void init() {
        Item.Properties props = new Item.Properties().maxStackSize(1).rarity(Rarity.RARE);
        disc = new CharmMusicDiscItem(this, "music_disc_charmonium", CharmoniumSounds.MUSIC_DISC, props, 0);
    }

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        musicClient = new MusicClient();
    }

    @SubscribeEvent
    public void onName(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof FoxEntity
            && event.getPlayer() != null
            && ((FoxEntity) event.getTarget()).getVariantType() == FoxEntity.Type.SNOW
        ) {
            FoxEntity fox = (FoxEntity) event.getTarget();
            ItemStack held = event.getPlayer().getHeldItem(event.getHand());
            if (!(held.getItem() instanceof NameTagItem)) return;
            if (fox.getDisplayName().getUnformattedComponentText().equals(NAME)) return;

            String name = held.getDisplayName().getUnformattedComponentText();
            if (name.equals(NAME)) {
                fox.setHeldItem(Hand.MAIN_HAND, new ItemStack(disc));
            }
        }
    }
}
