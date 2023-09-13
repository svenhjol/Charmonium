 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.core.BlockPos;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.entity.monster.Monster;
 import net.minecraft.world.level.block.Block;
 import net.minecraft.world.level.block.Blocks;
 import net.minecraft.world.phys.AABB;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.sound.ISoundType;
 import svenhjol.charmonium.sound.RepeatedWorldSound;
 import svenhjol.charmonium.sound.SoundHandler;
 import svenhjol.charmonium.sound.WorldSound;
 import svenhjol.charmony.helper.WorldHelper;

 import java.util.List;

 public class Mansion implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public Mansion() {
         SOUND = SoundEvent.createVariableRangeEvent(Charmonium.instance().makeId("world.mansion"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.mansion) return;

         handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 var bb = new AABB(player.blockPosition()).inflate(10);
                 List<Monster> monsters = level.getEntitiesOfClass(Monster.class, bb);

                 var optBlock1 = BlockPos.findClosestMatch(player.blockPosition(), 8, 8, pos -> {
                     Block block = level.getBlockState(pos).getBlock();
                     return block == Blocks.DARK_OAK_PLANKS;
                 });

                 var optBlock2 = BlockPos.findClosestMatch(player.blockPosition(), 8, 8, pos -> {
                     Block block = level.getBlockState(pos).getBlock();
                     return block == Blocks.BIRCH_PLANKS;
                 });

                 if (optBlock1.isPresent() && optBlock2.isPresent()) {
                     // Get a hostile mob's location as the source of the sound.
                     var optMonster = monsters.stream().findAny();
                     if (optMonster.isPresent()) {
                         setPos(optMonster.get().blockPosition());
                         return true;
                     }
                 }

                 return false;
             }

             @Override
             public boolean isValidPlayerCondition() {
                 return !WorldHelper.isBelowSeaLevel(player);
             }

             @Nullable
             @Override
             public SoundEvent getSound() {
                 return SOUND;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(200) + 400;
             }

             @Override
             public float getVolume() {
                 return 0.82F;
             }
         });
     }
 }
