package svenhjol.charmonium.module.underground_ambience;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import svenhjol.charmonium.annotation.Config;
import svenhjol.charmonium.annotation.Module;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.CharmoniumModule;
import svenhjol.charmonium.module.underground_ambience.UndergroundSounds.Cave;
import svenhjol.charmonium.module.underground_ambience.UndergroundSounds.DeepCave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Module(description = "Ambient background sound plays when underground.")
public class UndergroundAmbience extends CharmoniumModule {
    public Handler handler;

    @Config(name = "Valid dimensions", description = "Dimensions in which underground ambience will be played.")
    public static List<String> configDimensions = Arrays.asList(
        "minecraft:overworld"
    );

    @Config(name = "Play cave ambience", description = "If true, the cave ambience will be played. Cave ambience is normally a low frequency drone.")
    public static boolean playCaveAmbience = true;

    @Config(name = "Play deepcave ambience", description = "If true, the deepcave ambience will be played. This takes effect when the player is deep underground and at low light level.")
    public static boolean playDeepCaveAmbience = true;

    @Config(name = "Cave depth", description = "When the player is lower than this depth then cave background sound will be always be triggered.")
    public static int caveDepth = 48;

    @Config(name = "Light level", description = "When the light is lower than this level then cave and deepcave background sound will be triggered.")
    public static int lightLevel = 10;

    public static List<ResourceLocation> validDimensions = new ArrayList<>();

    @Override
    public void init() {
        configDimensions.forEach(dim -> validDimensions.add(new ResourceLocation(dim)));

        ClientEntityEvents.ENTITY_LOAD.register(this::handleEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(this::handleEntityUnload);
        ClientTickEvents.END_CLIENT_TICK.register(this::handleClientTick);
    }

    private void handleEntityLoad(Entity entity, Level level) {
        if (entity instanceof Player)
            trySetupSoundHandler((Player)entity);
    }

    private void handleEntityUnload(Entity entity, Level level) {
        if (handler != null)
            handler.stop();
    }

    private void handleClientTick(Minecraft client) {
        if (handler != null)
            handler.tick();
    }

    public void trySetupSoundHandler(Player player) {
        if (!(player instanceof LocalPlayer)) return;

        if (handler == null)
            handler = new Handler(player);

        handler.updatePlayer(player);
    }

    public static class Handler extends SoundHandler<UndergroundSound> {
        public Handler(@NotNull Player player) {
            super(player);

            Cave.init(this);
            DeepCave.init(this);
        }
    }
}
