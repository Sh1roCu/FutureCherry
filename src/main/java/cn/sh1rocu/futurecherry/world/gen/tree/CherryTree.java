package cn.sh1rocu.futurecherry.world.gen.tree;

import cn.sh1rocu.futurecherry.registration.FCFeatures;
import cn.sh1rocu.futurecherry.world.gen.tree.config.TreeDefaultConfig;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

//from Biomes O' Plenty Team
public class CherryTree extends TreeDefaultConfig {
    @Override
    protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random) {
        return (random.nextInt(3) == 0 ? FCFeatures.BIG_CHERRY_TREE : FCFeatures.CHERRY_TREE);
    }
}