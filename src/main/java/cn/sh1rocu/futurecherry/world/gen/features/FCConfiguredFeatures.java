package cn.sh1rocu.futurecherry.world.gen.features;

import cn.sh1rocu.futurecherry.FutureCherry;
import cn.sh1rocu.futurecherry.block.CherryBushBlock;
import cn.sh1rocu.futurecherry.registration.FCBlocks;
import cn.sh1rocu.futurecherry.registration.FCFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;

public class FCConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> CHERRY_TREE = register("cherry_tree", FCFeatures.CHERRY_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG__CHERRY_TREE = register("big_cherry_tree", FCFeatures.BIG_CHERRY_TREE.configured(Features.OAK.config()));

    public static final ConfiguredFeature<?, ?> CHERRY_TREE_BEES = register("cherry_tree_bees",
            FCFeatures.CHERRY_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));
    public static final ConfiguredFeature<?, ?> BIG_CHERRY_TREE_BEES = register("big_cherry_tree_bees",
            FCFeatures.BIG_CHERRY_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));

    public static final ConfiguredFeature<?, ?> CHERRY_GROVE_TREES = register("cherry_grove_trees",
            Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(
                    CHERRY_TREE_BEES.weighted(0.1F),
                    BIG_CHERRY_TREE_BEES.weighted(0.2F),
                    CHERRY_TREE.weighted(0.3F),
                    BIG__CHERRY_TREE.weighted(0.15F)),
                    CHERRY_TREE))).
            decorated(Features.Placements.HEIGHTMAP_SQUARE).
            decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.3F, 1)));

    public static final ConfiguredFeature<?, ?> PINK_PETALS = register("pink_petals",
            Feature.RANDOM_PATCH.configured((
                            new BlockClusterFeatureConfig.Builder(
                                    new SimpleBlockStateProvider(FCBlocks.PINK_PETALS.get().defaultBlockState().setValue(CherryBushBlock.FLOWER_AMOUNT, new Random(System.currentTimeMillis()).nextInt(4) + 1)),
                                    new SimpleBlockPlacer())).
                            tries(96).noProjection().build()).
                    decorated(Features.Placements.ADD_32).
                    decorated(Features.Placements.HEIGHTMAP_SQUARE.count(50)));

    public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(FutureCherry.MODID, name), feature);
    }
}