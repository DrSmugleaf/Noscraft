package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ModGuis {

    private static final @Nonnull GuiSpellBar SPELL_BAR = new GuiSpellBar();

    @SubscribeEvent
    public static void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiScreen gui = event.getGui();
        String format;
        if (gui instanceof GuiInventory) {
            format = "button.noscraft";
        } else if (gui instanceof GuiExpanded) {
            format = "button.normal";
        } else {
            return;
        }

        format = I18n.format(format);
        GuiNoscraftButton button = new GuiNoscraftButton((GuiContainer) gui, 26, 8, 10, 10, format);
        event.getButtonList().add(button);
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        SPELL_BAR.draw(event);
    }

    @SubscribeEvent
    public static void registerSlot(TextureStitchEvent.Pre event) {
        event.getMap().registerSprite(new ResourceLocation(Noscraft.MOD_ID, "items/slot/empty_fairy"));
    }

}
