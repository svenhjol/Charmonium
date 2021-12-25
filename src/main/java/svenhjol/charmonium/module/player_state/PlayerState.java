package svenhjol.charmonium.module.player_state;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import svenhjol.charm.api.event.ClientStateUpdateCallback;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.ClientModule;
import svenhjol.charmonium.loader.CharmModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ClientModule(mod = Charmonium.MOD_ID, description = "Updates player state from Charm, if present.", alwaysEnabled = true)
public class PlayerState extends CharmModule {
    public static List<ResourceLocation> WITHIN_STRUCTURES = new ArrayList<>();

    @Override
    public void register() {
        // Listen to state events from Charm.
        ClientStateUpdateCallback.EVENT.register(this::handleCharmStateUpdates);
    }

    private void handleCharmStateUpdates(CompoundTag tag) {
        var list = tag.getList("WithinStructures", 8);

        WITHIN_STRUCTURES.clear();
        WITHIN_STRUCTURES.addAll(list.stream()
            .map(Tag::getAsString)
            .map(ResourceLocation::new)
            .collect(Collectors.toList()));
    }
}
