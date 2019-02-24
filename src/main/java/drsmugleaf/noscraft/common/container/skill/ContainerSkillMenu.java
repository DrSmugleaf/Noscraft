package drsmugleaf.noscraft.common.container.skill;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 23/02/2019
 */
public class ContainerSkillMenu extends Container {

    public ContainerSkillMenu(@Nonnull EntityPlayer player) {}

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

}
