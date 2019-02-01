package drsmugleaf.noscraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ContainerExpanded extends Container {

    private static final EntityEquipmentSlot[] slots = new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};

    public ContainerExpanded(@Nonnull EntityPlayer player) {
        for (int i = 0; i < slots.length; i++) {
            EntityEquipmentSlot slot = slots[i];
            addSlotToContainer(new Slot(player.inventory, 36 + (3 - i), 8, 8 + i * 18) {
                @Override
                public int getSlotStackLimit() {
                    return 1;
                }

                @Override
                public boolean isItemValid(ItemStack stack) {
                    return stack.getItem().isValidArmor(stack, slot, player);
                }

                @Nullable
                @Override
                public String getSlotTexture() {
                    return ItemArmor.EMPTY_SLOT_NAMES[slot.getIndex()];
                }
            });
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

}
