package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.common.network.PacketOpenSkillMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 22/02/2019
 */
public class GuiSkillMenuButton extends GuiButton {

    private static final int ID = 389127451;
    private final @Nonnull GuiContainer PARENT_GUI;

    public GuiSkillMenuButton(@Nonnull GuiContainer parentGui, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(ID, x + parentGui.getGuiLeft(), y + parentGui.getGuiTop(), widthIn, heightIn, buttonText);
        PARENT_GUI = parentGui;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        boolean pressed = super.mousePressed(mc, mouseX, mouseY);

        if (pressed) {
            new PacketOpenSkillMenu().sendToServer();
        }

        return pressed;
    }

}
