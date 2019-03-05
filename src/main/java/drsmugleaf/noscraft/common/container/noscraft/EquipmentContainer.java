package drsmugleaf.noscraft.common.container.noscraft;

import drsmugleaf.noscraft.common.item.ItemEquippable;
import drsmugleaf.noscraft.common.item.equipment.Slots;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public class EquipmentContainer extends ItemStackHandler implements IItemHandlerModifiable {

    private boolean changed = false;
    private final @Nonnull Slots SLOT;

    public EquipmentContainer(@Nonnull Slots slot) {
        super(64);
        SLOT = slot;
    }

    @Override
    public void setSize(int size) {
        if (size < 1) {
            size = 64;
        }

        super.setSize(size);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof ItemEquippable && ((ItemEquippable) item).getSlot() == SLOT;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        if (stack.isEmpty() || isItemValid(slot, stack)) {
            super.setStackInSlot(slot, stack);
        }
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (!isItemValid(slot, stack)) {
            return stack;
        }

        return super.insertItem(slot, stack, simulate);
    }

    @Override
    protected void onContentsChanged(int slot) {
        setChanged(true);
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean bool) {
        changed = bool;
    }

}
