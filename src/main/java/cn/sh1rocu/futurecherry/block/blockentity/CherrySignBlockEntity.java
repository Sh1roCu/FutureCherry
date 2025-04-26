package cn.sh1rocu.futurecherry.block.blockentity;

import cn.sh1rocu.futurecherry.registration.FCBlockEntities;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CherrySignBlockEntity extends SignTileEntity {

    @Override
    public TileEntityType<?> getType() {
        return FCBlockEntities.CHERRY_SIGNS.get();
    }
}