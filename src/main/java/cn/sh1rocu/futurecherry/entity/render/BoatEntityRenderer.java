package cn.sh1rocu.futurecherry.entity.render;

import cn.sh1rocu.futurecherry.FutureCherry;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;

public class BoatEntityRenderer extends BoatRenderer {
    private static final ResourceLocation BOAT_TEXTURE =
            new ResourceLocation(FutureCherry.MODID, "textures/entity/boat/cherry.png");

    public BoatEntityRenderer(EntityRendererManager p_i46190_1_) {
        super(p_i46190_1_);
    }

    @Override
    public ResourceLocation getTextureLocation(BoatEntity boatEntity) {
        return BOAT_TEXTURE;
    }
}
