package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.keybinding.ModKeys;
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

    private static final @Nonnull ResourceLocation WIDGETS = new ResourceLocation(Noscraft.MOD_ID, "textures/gui/spell_bar.png");
    private static final int HOTBAR_WIDTH = 202;
    private static final int HOTBAR_HEIGHT = 22;

    public static boolean pressed = false;

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
        int width = (resolution.getScaledWidth() / 2) - 101;
        int height = resolution.getScaledHeight() - 22;
        drawTexturedModalRect(width, height, 0, 0, HOTBAR_WIDTH, HOTBAR_HEIGHT);
        mc.getTextureManager().bindTexture(new ResourceLocation(Noscraft.MOD_ID, "textures/abilities/hell_drop.png"));
        if (pressed) {
            GlStateManager.color(0.25F, 0.25F, 0.25F, 1F);
        }

        drawTexturedModalRect(width + HOTBAR_WIDTH - 22, height, 0, 0, 22, 22);
        drawString(Minecraft.getMinecraft().fontRenderer, ModKeys.ABILITY_0.getDisplayName(), width + HOTBAR_WIDTH - 22 + 2, height + 2, 0xFFFFFF);
        mc.getTextureManager().bindTexture(WIDGETS);
    }

}
