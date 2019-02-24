package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.container.skill.ContainerSkillMenu;
import drsmugleaf.noscraft.common.container.skill.SkillCapabilities;
import drsmugleaf.noscraft.common.skills.ISkill;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by DrSmugleaf on 22/02/2019
 */
public class GuiSkillMenu extends InventoryEffectRenderer {

    public static final int ID = 1;
    public static final @Nonnull ResourceLocation BACKGROUND = new ResourceLocation(Noscraft.MOD_ID, "textures/gui/skill_menu.png");
    public static final int BORDERS = 8;
    public static final int COLUMNS = GuiSkillBar.SLOTS;
    public static final int ROWS = 7;
    public static final int SLOT_HEIGHT = 16;
    public static final int SLOT_WIDTH = 16;
    public static final int SLOT_BORDER = 1;
    public static final int SLOT_REAL_HEIGHT = SLOT_HEIGHT + SLOT_BORDER * 2;
    public static final int SLOT_REAL_WIDTH = SLOT_WIDTH + SLOT_BORDER * 2;
    private final @Nonnull EntityPlayer PLAYER;
    private @Nullable GuiSkillButton BUTTON_DRAGGED = null;

    public GuiSkillMenu(@Nonnull EntityPlayer player) {
        super(new ContainerSkillMenu(player));
        xSize = 194;
        allowUserInput = true;
        PLAYER = player;
    }

    @Override
    public void initGui() {
        super.initGui();

        Map<Integer, ISkill> skillsLearned = PLAYER.getCapability(SkillCapabilities.CAPABILITY_SKILLS, null).getSkills();

        int i = 0;
        for (int row = 0; row < ROWS; row++) {
            int y = guiTop + BORDERS + SLOT_REAL_HEIGHT * row;

            for (int column = 0; column < COLUMNS && i < skillsLearned.size(); column++) {
                int x = guiLeft + BORDERS + SLOT_REAL_WIDTH * column;
                ISkill skill = skillsLearned.get(i);
                GuiSkillButton button = new GuiSkillButton(this, skill, COLUMNS + i, x, y, SLOT_WIDTH, SLOT_HEIGHT, skill.getName());

                addButton(button);

                i++;
            }
        }

        int y = guiTop + BORDERS * 2 + SLOT_REAL_HEIGHT * ROWS;
        for (int column = 0; column < COLUMNS; column++) {
            int x = guiLeft + BORDERS + SLOT_REAL_WIDTH * column;
            ISkill skill = ModGuis.getSkillBar().getSkill(column + 1);
            GuiSkillHotbarButton hotbarButton = new GuiSkillHotbarButton(
                    this,
                    skill,
                    column,
                    x,
                    y,
                    SLOT_WIDTH,
                    SLOT_HEIGHT,
                    skill == null ? "" : skill.getName()
            );

            addButton(hotbarButton);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(BACKGROUND);
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
        drawString(mc.fontRenderer, I18n.format("gui.skill_menu"), i + 5, j - 10, 0xFFFFFF);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        for (GuiButton button : buttonList) {
            button.drawButtonForegroundLayer(mouseX, mouseY);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Nullable
    private GuiButton getButtonUnderMouse() {
        for (GuiButton button : buttonList) {
            if (button.isMouseOver() && button.enabled) {
                return button;
            }
        }

        return null;
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (BUTTON_DRAGGED == null) {
            BUTTON_DRAGGED = (GuiSkillButton) getButtonUnderMouse();
            return;
        }

        if (BUTTON_DRAGGED.getSkill() == null) {
            return;
        }

        ResourceLocation texture = BUTTON_DRAGGED.getSkill().getTexture();
        ResourceLocation location = new ResourceLocation(texture.getResourceDomain(), "textures/" + texture.getResourcePath() + ".png");
        mc.getTextureManager().bindTexture(location);
        drawModalRectWithCustomSizedTexture(
                mouseX,
                mouseY,
                0,
                0,
                GuiSkillMenu.SLOT_REAL_WIDTH - 1,
                GuiSkillMenu.SLOT_REAL_HEIGHT - 1,
                GuiSkillMenu.SLOT_REAL_WIDTH - 1,
                GuiSkillMenu.SLOT_REAL_HEIGHT - 1
        );
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (BUTTON_DRAGGED == null || BUTTON_DRAGGED.getSkill() == null) {
            BUTTON_DRAGGED = null;
            return;
        }

        GuiButton button = getButtonUnderMouse();
        if (!(button instanceof GuiSkillHotbarButton)) {
            BUTTON_DRAGGED = null;
            return;
        }

        GuiSkillHotbarButton hotbarButton = (GuiSkillHotbarButton) button;
        hotbarButton.setSkill(BUTTON_DRAGGED.getSkill());
        ModGuis.getSkillBar().setSkill(hotbarButton.id + 1, BUTTON_DRAGGED.getSkill());
        BUTTON_DRAGGED = null;
    }

}
