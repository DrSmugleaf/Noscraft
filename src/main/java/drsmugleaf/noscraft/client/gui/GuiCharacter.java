package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.container.noscraft.ContainerCharacter;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class GuiCharacter extends GuiInventoryMod {

    public static final int ID = 0;
    public static final @Nonnull ResourceLocation BACKGROUND = new ResourceLocation(Noscraft.MOD_ID, "textures/gui/character_inventory.png");
    private float oldMouseX;
    private float oldMouseY;

    public GuiCharacter(@Nonnull EntityPlayer player) {
        super(new ContainerCharacter(player));
        allowUserInput = true;
    }

    private void resetGui() {
        guiLeft = (width - xSize) / 2;
    }

    @Override
    public void updateScreen() {
        resetGui();
    }

    @Override
    public void initGui() {
        buttonList.clear();
        super.initGui();
        resetGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        oldMouseX = (float) mouseX;
        oldMouseY = (float) mouseY;
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);

//        fontRenderer.drawString("13", guiLeft + 97, guiTop + 9, Elements.FIRE.getHexColor());
//        fontRenderer.drawString("40", guiLeft + 97, guiTop + 27, Elements.WATER.getHexColor());
//        fontRenderer.drawString("20", guiLeft + 97, guiTop + 45, Elements.LIGHT.getHexColor());
//        fontRenderer.drawString("31", guiLeft + 97, guiTop + 63, Elements.DARKNESS.getHexColor());

//        FairyContainer container = PLAYER.getCapability(FairyCapabilities.CAPABILITY_FAIRIES, null);
//        ItemStack stack = container.getStackInSlot(0);
//        if (!stack.isEmpty()) {
//            ItemFairy fairy = (ItemFairy) stack.getItem();
//            fontRenderer.drawString(String.valueOf(fairy.getLevel()), guiLeft + 153, guiTop + 27, fairy.getElement().getHexColor());
//        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(BACKGROUND);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        for (Slot slot : inventorySlots.inventorySlots) {
            if (slot.getHasStack() && slot.getSlotStackLimit() == 1) {
                drawTexturedModalRect(guiLeft + slot.xPos, guiTop + slot.yPos, 200, 0, 16, 16);
            }
        }

        int x = guiLeft + 51;
        int y = guiTop + 75;
        GuiInventory.drawEntityOnScreen(x, y, 30, (float)(x) - oldMouseX, (float)(y - 50) - oldMouseY, mc.player);
    }

}
