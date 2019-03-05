package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.common.skills.ISkill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 22/02/2019
 */
public class GuiSkillButton extends GuiButtonMod {

    protected @Nullable ISkill SKILL;

    public GuiSkillButton(@Nonnull GuiSkillMenu parentGui, @Nullable ISkill skill, int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(parentGui, buttonId, x, y, widthIn, heightIn, buttonText);
        SKILL = skill;
    }

    @Override
    public boolean doesRender() {
        return true;
    }

    @Nonnull
    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(SKILL.getTexture().getResourceDomain(), "textures/" + SKILL.getTexture().getResourcePath() + ".png");
    }

    @Nullable
    public ISkill getSkill() {
        return SKILL;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        int hoverState = getHoverState(hovered);

        if (hoverState == 2) {
            drawCenteredString(font, I18n.format(displayString), mouseX - PARENT_GUI.getGuiLeft(), mouseY - PARENT_GUI.getGuiTop() + 5, 0xFFFFFF);
        }
    }

    public void draw(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks, int x, int y) {
        visible = doesRender();
        hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

        if (visible && SKILL != null) {
            mc.getTextureManager().bindTexture(getTexture());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, 0);

            drawModalRectWithCustomSizedTexture(x - 1, y - 1, 0, 0, GuiSkillMenu.SLOT_REAL_WIDTH - 1, GuiSkillMenu.SLOT_REAL_HEIGHT - 1, GuiSkillMenu.SLOT_REAL_WIDTH - 1, GuiSkillMenu.SLOT_REAL_HEIGHT - 1);

            GlStateManager.popMatrix();

            mouseDragged(mc, mouseX, mouseY);
        }
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        draw(mc, mouseX, mouseY, partialTicks, x, y);
    }

}
