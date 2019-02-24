package drsmugleaf.noscraft.common.container.noscraft;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class FairyContainer extends ItemStackHandler implements IItemHandlerModifiable {

    private boolean changed = false;

    public FairyContainer() {
        super(1);
    }

    @Override
    public void setSize(int size) {
        if (size < 1) {
            size = 1;
        }

        super.setSize(size);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return !stack.isEmpty() && stack.hasCapability(FairyCapabilities.CAPABILITY_ITEM_FAIRY, null);
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
