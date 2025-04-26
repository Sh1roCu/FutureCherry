package cn.sh1rocu.futurecherry.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.tileentity.TileEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityType.class)
public abstract class BlockEntityTypeMixin {

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void fc$isValid(Block block, CallbackInfoReturnable<Boolean> info) {
        if (TileEntityType.SIGN.equals(this) && (block instanceof StandingSignBlock || block instanceof WallSignBlock)) {
            info.setReturnValue(true);
        }
    }
}