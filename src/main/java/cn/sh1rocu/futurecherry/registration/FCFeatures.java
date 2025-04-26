package cn.sh1rocu.futurecherry.registration;

import cn.sh1rocu.futurecherry.FutureCherry;
import cn.sh1rocu.futurecherry.world.gen.features.tree.BasicTreeFeature;
import cn.sh1rocu.futurecherry.world.gen.features.tree.BigTreeFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class FCFeatures {
    public static void init() {

    }

    public static final Feature<BaseTreeFeatureConfig> CHERRY_TREE = register("cherry_tree", new BasicTreeFeature.Builder().
            log(FCBlocks.CHERRY_LOG.get().defaultBlockState()).
            leaves(FCBlocks.CHERRY_LEAVES.get().defaultBlockState()).
            create());

    public static final Feature<BaseTreeFeatureConfig> BIG_CHERRY_TREE = register("big_cherry_tree", new BigTreeFeature.Builder().
            log(FCBlocks.CHERRY_LOG.get().defaultBlockState()).
            leaves(FCBlocks.CHERRY_LEAVES.get().defaultBlockState()).
            create());

    public static <FC extends IFeatureConfig, F extends Feature<FC>> F register(String name, F feature) {
        feature.setRegistryName(new ResourceLocation(FutureCherry.MODID, name));
        ForgeRegistries.FEATURES.register(feature);
        return feature;
    }
}
