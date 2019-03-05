package drsmugleaf.noscraft.common.container.noscraft;

import drsmugleaf.noscraft.Noscraft;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public class SlotEquipment extends SlotItemHandler {

    private final @Nonnull String NAME;

    public SlotEquipment(@Nonnull String name, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        NAME = name;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Nullable
    @Override
    public String getSlotTexture() {
        return Noscraft.MOD_ID + ":gui/slot/" + NAME;
    }

}
