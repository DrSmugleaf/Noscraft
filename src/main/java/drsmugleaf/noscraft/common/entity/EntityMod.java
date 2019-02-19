package drsmugleaf.noscraft.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 17/02/2019
 */
public abstract class EntityMod extends Entity {

    public EntityMod(World worldIn) {
        super(worldIn);
    }

    public EntityMod(World worldIn, double x, double y, double z) {
        super(worldIn);
        setPosition(x, y, z);
    }

    public void spawnTrailParticles() {}

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(@Nonnull NBTTagCompound compound) {}

    @Override
    protected void writeEntityToNBT(@Nonnull NBTTagCompound compound) {}

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (world.isRemote) {
            spawnTrailParticles();
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
    }

}
