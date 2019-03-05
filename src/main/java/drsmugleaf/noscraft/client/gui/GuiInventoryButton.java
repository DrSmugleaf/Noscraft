package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.network.PacketOpenNormalInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public class GuiInventoryButton extends GuiButtonMod {

    private static final int ID = 217963572;

    @Nonnull
    public static final ResourceLocation INVENTORY_ICON = new ResourceLocation(Noscraft.MOD_ID, "textures/gui/inventory_icon.png");

    public GuiInventoryButton(@Nonnull GuiContainer parentGui, int x, int y, int widthIn, int heightIn) {
        super(parentGui, ID, x + parentGui.getGuiLeft(), y + parentGui.getGuiTop(), widthIn, heightIn, getDisplayString(parentGui));
    }

    @Nonnull
    public static String getDisplayString(@Nonnull GuiContainer unused) {
        return I18n.format("button.inventory");
    }

    @Override
    public boolean doesRender() {
        return true;
    }

    @Nonnull
    @Override
    public ResourceLocation getTexture() {
        return INVENTORY_ICON;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        boolean pressed = super.mousePressed(mc, mouseX, mouseY);

        if (pressed) {
            GuiInventory gui = new GuiInventory(mc.player);
            mc.displayGuiScreen(gui);
            new PacketOpenNormalInventory().sendToServer();
        }

        return pressed;
    }

}
