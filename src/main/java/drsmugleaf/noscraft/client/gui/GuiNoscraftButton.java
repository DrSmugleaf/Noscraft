package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.common.network.PacketOpenNormalInventory;
import drsmugleaf.noscraft.common.network.PacketOpenNoscraftInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class GuiNoscraftButton extends GuiButton {

    private static final int ID = 1439563;
    private final @Nonnull GuiContainer PARENT_GUI;

    public GuiNoscraftButton(@Nonnull GuiContainer parentGui, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(ID, x, parentGui.getGuiTop() + y, widthIn, heightIn, buttonText);
        PARENT_GUI = parentGui;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        boolean pressed = super.mousePressed(mc, mouseX - PARENT_GUI.getGuiLeft(), mouseY);

        if (pressed) {
            if (PARENT_GUI instanceof GuiInventory) {
                new PacketOpenNoscraftInventory().sendToServer();
            } else {
                ((GuiExpanded) PARENT_GUI).displayNormalInventory();
                new PacketOpenNormalInventory().sendToServer();
            }
        }

        return pressed;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            int x = this.x + PARENT_GUI.getGuiLeft();
            FontRenderer font = mc.fontRenderer;

            mc.getTextureManager().bindTexture(GuiExpanded.BACKGROUND);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            int hoverState = getHoverState(hovered);

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, 200);

            drawTexturedModalRect(x, y, 200, 48, 10, 10);
            if (hoverState == 2) {
                drawCenteredString(font, I18n.format(displayString), x + 5, y + height, 0xFFFFFF);
            }

            GlStateManager.popMatrix();

            mouseDragged(mc, mouseX, mouseY);
        }
    }

}
