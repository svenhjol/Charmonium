package svenhjol.charmonium.module.player_state;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import svenhjol.charm.api.CharmNetworkReferences;
import svenhjol.charm.api.CharmPlayerStateKeys;
import svenhjol.charmonium.helper.NetworkHelper;
import svenhjol.charmonium.loader.CharmModule;

public class PlayerState extends CharmModule {
    private static final ResourceLocation MSG_CLIENT = new ResourceLocation(CharmNetworkReferences.ClientUpdatePlayerState.getSerializedName());

    // special state properties fetched from server
    public static boolean insideOverworldRuin;

    @Override
    public void register() {
        // listen to network requests from Charm
        ClientPlayNetworking.registerGlobalReceiver(MSG_CLIENT, this::handleUpdatePlayerState);
    }

    private void handleUpdatePlayerState(Minecraft client, ClientPacketListener handler, FriendlyByteBuf data, PacketSender sender) {
        CompoundTag nbt = NetworkHelper.decodeNbt(data);
        if (nbt == null) return;

        client.execute(() -> {
            insideOverworldRuin = nbt.getBoolean(CharmPlayerStateKeys.InsideOverworldRuin.toString());
        });
    }
}
