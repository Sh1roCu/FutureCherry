package cn.sh1rocu.futurecherry.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CherryBushBlock extends BushBlock implements IGrowable {
    public static final int MIN_FLOWERS = 1;
    public static final int MAX_FLOWERS = 4;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty FLOWER_AMOUNT = IntegerProperty.create("flower_amount", 1, 4);

    public CherryBushBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FLOWER_AMOUNT, 1));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockItemUseContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().getItem().equals(this.asItem()) && state.getValue(FLOWER_AMOUNT) < 4 || super.canBeReplaced(state, context);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        VoxelShape[] avoxelshape = new VoxelShape[]{Block.box(8.0F, 0.0F, 8.0F, 16.0F, 3.0F, 16.0F), Block.box(8.0F, 0.0F, 0.0F, 16.0F, 3.0F, 8.0F), Block.box(0.0F, 0.0F, 0.0F, 8.0F, 3.0F, 8.0F), Block.box(0.0F, 0.0F, 8.0F, 8.0F, 3.0F, 16.0F)};
        VoxelShape voxelshape = VoxelShapes.empty();

        for (int i = 0; i < state.getValue(FLOWER_AMOUNT); ++i) {
            int j = Math.floorMod(i - state.getValue(FACING).get2DDataValue(), 4);
            voxelshape = VoxelShapes.or(voxelshape, avoxelshape[j]);
        }

        return voxelshape.isEmpty() ? VoxelShapes.empty() : VoxelShapes.box(voxelshape.min(Direction.Axis.X), voxelshape.min(Direction.Axis.Y), voxelshape.min(Direction.Axis.Z), voxelshape.max(Direction.Axis.X), voxelshape.max(Direction.Axis.Y), voxelshape.max(Direction.Axis.Z));
    }

    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos());
        return blockState.is(this) ? blockState.setValue(FLOWER_AMOUNT, Math.min(4, blockState.getValue(FLOWER_AMOUNT) + 1)) : this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, FLOWER_AMOUNT);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld serverWorld, Random random, BlockPos blockPos, BlockState blockState) {
        int i = blockState.getValue(FLOWER_AMOUNT);
        if (i < 4) {
            serverWorld.setBlock(blockPos, blockState.setValue(FLOWER_AMOUNT, i + 1), 2);
        } else {
            popResource(serverWorld, blockPos, new ItemStack(this));
        }
    }
}