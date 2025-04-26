package cn.sh1rocu.futurecherry.block;

import cn.sh1rocu.futurecherry.block.blockentity.CherrySignBlockEntity;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CherryWallSignBlock extends WallSignBlock {
    public CherryWallSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new CherrySignBlockEntity();
    }
}