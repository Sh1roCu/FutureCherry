package cn.sh1rocu.futurecherry.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CherryLeavesParticle extends SpriteTexturedParticle {
    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final int INITIAL_LIFETIME = 300;
    private static final int CURVE_ENDPOINT_TIME = 300;
    private float rotSpeed;
    private final float particleRandom;
    private final float spinAcceleration;

    public CherryLeavesParticle(ClientWorld world, double x, double y, double z, IAnimatedSprite spriteProvider) {
        super(world, x, y, z);
        this.setSprite(spriteProvider.get(this.random.nextInt(12), 12));
        this.rotSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -30.0D : 30.0D);
        this.particleRandom = this.random.nextFloat();
        this.spinAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -5.0D : 5.0D);
        this.lifetime = 300;
        this.gravity = 0.25F * 1.2F * ACCELERATION_SCALE;
        float f = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.scale(f * 2);
        this.setSize(f * 2, f * 2);
        this.alpha = 1.0F;
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float f = (float) (300 - this.lifetime);
            float g = Math.min(f / 300.0F, 1.0F);
            double d = Math.cos(Math.toRadians(this.particleRandom * 60.0F)) * 2.0D * Math.pow(g, 1.25D);
            double e = Math.sin(Math.toRadians(this.particleRandom * 60.0F)) * 2.0D * Math.pow(g, 1.25D);
            this.xd += d * 0.0025F;
            this.zd += e * 0.0025F;
            this.yd -= this.gravity;
            this.rotSpeed += this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll += this.rotSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || this.lifetime < 299 && (this.xd == 0.0D || this.zd == 0.0D)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd *= this.alpha;
                this.yd *= this.alpha;
                this.zd *= this.alpha;
            }
        }
    }
}