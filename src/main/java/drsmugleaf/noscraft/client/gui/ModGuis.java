package drsmugleaf.noscraft.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ModGuis {

    @SubscribeEvent
    public static void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiScreen gui = event.getGui();
        String format;
        if (gui instanceof GuiInventory) {
            format = "button.noscraft";
        } else if (gui instanceof GuiExpanded) {
            format = "button.normal";
        } else {
            return;
        }

        format = I18n.format(format);
        GuiNoscraftButton button = new GuiNoscraftButton((GuiContainer) gui, 26, 8, 10, 10, format);
        event.getButtonList().add(button);
    }

}
