package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public abstract class GuiButtonMod extends GuiButton {

    @Nonnull
    protected final GuiContainer PARENT_GUI;

    public GuiButtonMod(@Nonnull GuiContainer parentGui, int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
        PARENT_GUI = parentGui;
    }

    public GuiButtonMod(@Nonnull GuiContainer parentGui, int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        PARENT_GUI = parentGui;
    }

    public abstract boolean doesRender();

    @Nonnull
    public abstract ResourceLocation getTexture();

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        visible = doesRender();

        if (visible) {
            FontRenderer font = mc.fontRenderer;

            mc.getTextureManager().bindTexture(getTexture());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            int hoverState = getHoverState(hovered);

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, 200);

            drawModalRectWithCustomSizedTexture(x, y, 0, 0, getButtonWidth(), height, 16, 16);
            if (hoverState == 2) {
                drawCenteredString(font, I18n.format(displayString), x + width / 2, y - height / 2, 0xFFFFFF);
            }

            GlStateManager.popMatrix();

            mouseDragged(mc, mouseX, mouseY);
        }
    }

}
