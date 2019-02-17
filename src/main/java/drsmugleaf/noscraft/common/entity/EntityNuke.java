package drsmugleaf.noscraft.common.entity;

import drsmugleaf.noscraft.client.renderer.entity.EntityModProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class EntityNuke extends EntityModProjectile {

    public EntityNuke(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityNuke(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityNuke(World worldIn) {
        super(worldIn);
    }

    private void explode(double x, double y, double z) {
        world.newExplosion(this, x, y, z, 12.0F, false, false);
    }

    @Override
    public void spawnTrailParticles() {
        world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, posX, posY - 0.3D, posZ, rand.nextGaussian() * 0.05D, -motionY * 0.5D, rand.nextGaussian() * 0.05D);
    }

    @Override
    protected void onHit(@Nonnull RayTraceResult result) {
        explode(posX, posY, posZ);
        explode(posX, posY, posZ + 4);
        explode(posX, posY, posZ - 4);
        explode(posX + 4, posY, posZ + 4);
        explode(posX - 4, posY, posZ - 4);

        super.onHit(result);
    }

}
