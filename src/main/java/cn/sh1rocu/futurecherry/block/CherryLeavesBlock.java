package cn.sh1rocu.futurecherry.block;

import cn.sh1rocu.futurecherry.registration.FCParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class CherryLeavesBlock extends LeavesBlock {
    public CherryLeavesBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        super.animateTick(state, world, pos, random);
        if (random.nextInt(3) == 0) {
            BlockPos blockPos = pos.below();
            BlockState blockState = world.getBlockState(blockPos);
            if (!isFaceFull(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                spawnParticle(world, pos, random, FCParticleTypes.CHERRY_LEAVES.get());
            }
        }
    }

    public static void spawnParticle(World world, BlockPos pos, Random random, BasicParticleType effect) {
        double d = (double) pos.getX() + random.nextDouble();
        double e = (double) pos.getY() - 0.05D;
        double f = (double) pos.getZ() + random.nextDouble();
        world.addParticle(effect, d, e, f, 0.0D, 0.0D, 0.0D);
    }
}
