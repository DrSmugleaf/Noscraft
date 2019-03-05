package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.network.PacketOpenInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class GuiCharacterButton extends GuiButtonMod {

    private static final int ID = 1439367563;

    @Nonnull
    private static final ResourceLocation CHARACTER_ICON = new ResourceLocation(Noscraft.MOD_ID, "textures/gui/character_icon.png");

    public GuiCharacterButton(@Nonnull GuiContainer parentGui, int x, int y, int widthIn, int heightIn) {
        super(parentGui, ID, x + parentGui.getGuiLeft(), y + parentGui.getGuiTop(), widthIn, heightIn, getDisplayString(parentGui));
    }

    @Nonnull
    public static String getDisplayString(@Nonnull GuiContainer parentGui) {
        return I18n.format("button.character");
    }

    @Override
    public boolean doesRender() {
        return true;
    }

    @Nonnull
    @Override
    public ResourceLocation getTexture() {
        return CHARACTER_ICON;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        boolean pressed = super.mousePressed(mc, mouseX, mouseY);

        if (pressed) {
            new PacketOpenInventory(GuiCharacter.ID).sendToServer();
        }

        return pressed;
    }

}
