package drsmugleaf.noscraft.common.container.noscraft;

import drsmugleaf.noscraft.common.item.equipment.Slots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ContainerCharacter extends Container {

    private static final @Nonnull EntityEquipmentSlot[] slots = new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};

    @Nonnull
    private final Equipment EQUIPMENT;

    public ContainerCharacter(@Nonnull EntityPlayer player) {
        EQUIPMENT = player.getCapability(EquipmentCapabilities.CAPABILITY_EQUIPMENT, null);

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

        int x;
        int y;
        Map<Slots, EquipmentContainer> slots = EQUIPMENT.getSlots();
        for (Slots slot : Slots.values()) {
            EquipmentContainer container = slots.get(slot);
            x = 124 + slot.getX();
            y = 45 + slot.getY();
            addSlotToContainer(slot.getSlot(container, x, y));
        }

        for (int column = 0; column < 9; column++) {
            x = 8 + column * 18;
            y = 142;
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

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

}
