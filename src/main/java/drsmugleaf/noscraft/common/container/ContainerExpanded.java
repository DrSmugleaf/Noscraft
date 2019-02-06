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

    private static final @Nonnull EntityEquipmentSlot[] slots = new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    private final @Nonnull FairyContainer fairy;

    public ContainerExpanded(@Nonnull EntityPlayer player) {
        fairy = player.getCapability(FairyCapabilities.CAPABILITY_FAIRIES, null);

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

        addSlotToContainer(new SlotFairy(fairy, 0, 135, 8));

        for (int column = 0; column < 9; column++) {
            int x = 8 + column * 18;
            int y = 142;
            addSlotToContainer(new Slot(player.inventory, column, x, y));

            for (int row = 0; row < 3; row++) {
                int index = column + (row + 1) * 9;
                y = 84 + row * 18;
                addSlotToContainer(new Slot(player.inventory, index, x, y));
            }
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

}
