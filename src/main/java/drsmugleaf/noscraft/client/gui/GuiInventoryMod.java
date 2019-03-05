package drsmugleaf.noscraft.client.gui;

import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public abstract class GuiInventoryMod extends InventoryEffectRenderer {

    public GuiInventoryMod(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

}
