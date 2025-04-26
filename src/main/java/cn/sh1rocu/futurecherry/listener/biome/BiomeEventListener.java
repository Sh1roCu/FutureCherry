package cn.sh1rocu.futurecherry.listener.biome;

import cn.sh1rocu.futurecherry.FutureCherry;
import cn.sh1rocu.futurecherry.world.gen.features.FCConfiguredFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = FutureCherry.MODID)
public class BiomeEventListener {
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent ev) {
        if (ev.getName() != null) {
            RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, ev.getName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
            if (ev.getName().getPath().equals("cherry_grove")) {
    /*            ev.setCategory(Biome.Category.EXTREME_HILLS);
                ev.setScale(0.1F);
                ev.setDepth(0.5F);
                ev.setClimate(new Biome.Climate(Biome.RainType.RAIN, 0.5F, Biome.TemperatureModifier.NONE, 0.8F));
                ev.setEffects(new BiomeAmbience.Builder().
                        fogColor(12638463).
                        foliageColorOverride(11983713).
                        grassColorOverride(11983713).
                        ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_CAVE, 6000, 8, 2.0)).
                        skyColor(8103167).
                        waterColor(6141935).
                        waterFogColor(6141935).
                        build());*/
                BiomeGenerationSettingsBuilder builder = ev.getGeneration();
                builder.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
                DefaultBiomeFeatures.addDefaultSprings(builder);
                DefaultBiomeFeatures.addSurfaceFreezing(builder);
                //structures
                DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
                builder.addStructureStart(StructureFeatures.VILLAGE_PLAINS);
                //underground
                DefaultBiomeFeatures.addDefaultCarvers(builder);
                DefaultBiomeFeatures.addDefaultLakes(builder);
                DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
                DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
                DefaultBiomeFeatures.addDefaultOres(builder);
                //vegetation
                builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FCConfiguredFeatures.CHERRY_GROVE_TREES);
                builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FCConfiguredFeatures.PINK_PETALS);
                builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FLOWER_PLAIN_DECORATED);
                //entities
                MobSpawnInfoBuilder spawnsBuilder = ev.getSpawns();
                spawnsBuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 8, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PANDA, 80, 1, 2));
                spawnsBuilder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
                spawnsBuilder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
            }
        }
    }
}