package cn.sh1rocu.futurecherry.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class BaseCherryBlock extends RotatedPillarBlock {
    private final Supplier<BlockState> strippedBlockState;

    public BaseCherryBlock(Properties properties, Supplier<Block> strippedBlock) {
        super(properties);
        this.strippedBlockState = () -> strippedBlock == null ? null : strippedBlock.get().defaultBlockState();
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (stack.getToolTypes().contains(ToolType.AXE))
            return this.strippedBlockState.get().setValue(AXIS, state.getValue(AXIS));
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
