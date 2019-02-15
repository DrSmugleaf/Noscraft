package drsmugleaf.noscraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public class GuiSpellBar extends Gui {

    private static final @Nonnull ResourceLocation WIDGETS = new ResourceLocation("minecraft", "textures/gui/widgets.png");
    private static final int HOTBAR_WIDTH = 182;
    private static final int HOTBAR_HEIGHT = 22;

    public GuiSpellBar() {}

    public void draw(@Nonnull RenderGameOverlayEvent.Pre event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();

        mc.getTextureManager().bindTexture(WIDGETS);
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();

        ScaledResolution resolution = event.getResolution();
        int width = resolution.getScaledWidth() - (int) (HOTBAR_WIDTH * 1.82);
        int height = resolution.getScaledHeight() - HOTBAR_HEIGHT;
        drawTexturedModalRect(width, height, 0, 0, HOTBAR_WIDTH, HOTBAR_HEIGHT);
    }

}
