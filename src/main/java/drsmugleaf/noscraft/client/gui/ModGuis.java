package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.item.equipment.Slots;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
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

        if (!(gui instanceof GuiInventory || gui instanceof GuiInventoryMod)) {
            return;
        }

        GuiContainer guiContainer = (GuiContainer) gui;
        int x = 173 + (152 - guiContainer.getGuiLeft());
        GuiInventoryButton inventory = new GuiInventoryButton(guiContainer, x - 16, -16, 16, 16);
        GuiCharacterButton character = new GuiCharacterButton(guiContainer, x - 32, -16, 16, 16);
        GuiSkillMenuButton skill = new GuiSkillMenuButton(guiContainer, x - 48, -16, 16, 16);
        event.getButtonList().add(character);
        event.getButtonList().add(inventory);
        event.getButtonList().add(skill);
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        SKILL_BAR.draw(event);
    }

    @SubscribeEvent
    public static void onTextureStitchEventPre(TextureStitchEvent.Pre event) {
        for (Slots slot : Slots.values()) {
            String slotName = slot.getName().toLowerCase().replaceAll(" ", "_");
            event.getMap().registerSprite(new ResourceLocation(Noscraft.MOD_ID, "gui/slot/" + slotName));
        }
    }

    @Nonnull
    public static GuiSkillBar getSkillBar() {
        return SKILL_BAR;
    }

}
