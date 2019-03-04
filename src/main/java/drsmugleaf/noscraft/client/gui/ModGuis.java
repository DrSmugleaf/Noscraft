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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class ModGuis {

    private static final @Nonnull GuiSkillBar SKILL_BAR = new GuiSkillBar();

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
        GuiSkillMenuButton button2 = new GuiSkillMenuButton((GuiContainer) gui, 46, 8, 10, 10, format);
        event.getButtonList().add(button);
        event.getButtonList().add(button2);
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        SKILL_BAR.draw(event);
    }

    @SubscribeEvent
    public static void onTextureStitchEventPre(TextureStitchEvent.Pre event) {
        event.getMap().registerSprite(new ResourceLocation(Noscraft.MOD_ID, "slot/empty_fairy"));
    }

    @Nonnull
    public static GuiSkillBar getSkillBar() {
        return SKILL_BAR;
    }

}
