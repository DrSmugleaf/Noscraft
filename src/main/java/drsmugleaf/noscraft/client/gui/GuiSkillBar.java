package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.keybinding.KeyBindingSkill;
import drsmugleaf.noscraft.client.keybinding.ModKeys;
import drsmugleaf.noscraft.common.skills.ISkill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public class GuiSkillBar extends Gui {

    private static final @Nonnull ResourceLocation SKILL_BAR = new ResourceLocation(Noscraft.MOD_ID, "textures/gui/skill_bar.png");
    public static final int SLOTS = 10;
    public static final int HOTBAR_OUTLINE = 2;
    public static final int HOTBAR_SLOT_SIZE = 20;
    public static final int HOTBAR_WIDTH = HOTBAR_SLOT_SIZE * SLOTS + HOTBAR_OUTLINE;
    public static final int HOTBAR_HEIGHT = HOTBAR_SLOT_SIZE + HOTBAR_OUTLINE;

    @Nonnull
    private final Map<KeyBinding, ISkill> SKILLS = new HashMap<>();

    public GuiSkillBar() {}

    @Nullable
    public ISkill getSkill(int id) {
        KeyBindingSkill bind = ModKeys.getBind(id);
        if (bind == null) {
            return null;
        }

        return SKILLS.get(bind);
    }

    public void setSkill(int id, @Nullable ISkill skill) {
        KeyBindingSkill bind = ModKeys.getBind(id);
        if (bind == null) {
            return;
        }

        SKILLS.put(bind, skill);
    }

    public int getHeightForSlot(@Nonnull RenderGameOverlayEvent.Pre event, int slot) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return 0;
        }

        return event.getResolution().getScaledHeight() - HOTBAR_HEIGHT + (HOTBAR_OUTLINE / 2);
    }

    public int getWidthForSlot(@Nonnull RenderGameOverlayEvent.Pre event, int slot) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return 0;
        }

        return (event.getResolution().getScaledWidth() / 2) - 101 + HOTBAR_WIDTH - (HOTBAR_SLOT_SIZE * (SLOTS - slot + 1)) - 1;
    }

    public void draw(@Nonnull RenderGameOverlayEvent.Pre event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(SKILL_BAR);

        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();

        ScaledResolution resolution = event.getResolution();
        int width = (resolution.getScaledWidth() / 2) - 101;
        int height = resolution.getScaledHeight() - 22;
        drawTexturedModalRect(width, height, 0, 0, HOTBAR_WIDTH, HOTBAR_HEIGHT);
        mc.getTextureManager().bindTexture(new ResourceLocation(Noscraft.MOD_ID, "textures/skills/destroyer_small.png"));

        for (int slot = 1; slot < 11; slot++) {
            KeyBindingSkill bind = ModKeys.getBind(slot);
            if (bind == null) {
                return;
            }

            ISkill skill = SKILLS.get(bind);
            if (skill == null) {
                continue;
            }

            ResourceLocation location = new ResourceLocation(skill.getTexture().getResourceDomain(), "textures/" + skill.getTexture().getResourcePath() + ".png");
            Minecraft.getMinecraft().getTextureManager().bindTexture(location);

            if (skill.isOnCooldown()) {
                GlStateManager.color(0.25F, 0.25F, 0.25F, 1F);
            } else {
                GlStateManager.color(1F, 1F, 1F, 1F);
            }

            int skillHeight = getHeightForSlot(event, slot);
            int skillWidth = getWidthForSlot(event, slot);
            int slotSize = HOTBAR_SLOT_SIZE;
            drawModalRectWithCustomSizedTexture(skillWidth, skillHeight, 0, 0, slotSize, slotSize, slotSize, slotSize);

            skillHeight += GuiSkillBar.HOTBAR_OUTLINE;
            skillWidth += GuiSkillBar.HOTBAR_OUTLINE;
            drawString(Minecraft.getMinecraft().fontRenderer, bind.getDisplayName(), skillWidth, skillHeight, 0xFFFFFF);
        }
    }

    public void cast(@Nonnull KeyBindingSkill bind) {
        ISkill skill = SKILLS.get(bind);
        if (skill == null) {
            return;
        }

        skill.cast();
    }

}
