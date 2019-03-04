package drsmugleaf.noscraft.common.container.noscraft;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class SlotFairy extends SlotItemHandler {

    public SlotFairy(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Nullable
    @Override
    public String getSlotTexture() {
        return "noscraft:slot/empty_fairy";
    }

}
