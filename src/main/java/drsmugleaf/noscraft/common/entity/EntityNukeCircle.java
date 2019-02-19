package drsmugleaf.noscraft.common.entity;

import net.minecraft.world.World;

/**
 * Created by DrSmugleaf on 18/02/2019
 */
public class EntityNukeCircle extends EntityMod {

    private boolean spawnedNuke = false;

    public EntityNukeCircle(World worldIn) {
        super(worldIn);
    }

    public EntityNukeCircle(World worldIn, double x, double y, double z) {
        super(worldIn, x, y + 1, z);
    }

    @Override
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (ticksExisted > 80 && !spawnedNuke) {
            world.spawnEntity(new EntityNuke(world, posX, posY, posZ, this));
            spawnedNuke = true;
        }

        if (ticksExisted > 300) {
            setDead();
        }
    }

}
