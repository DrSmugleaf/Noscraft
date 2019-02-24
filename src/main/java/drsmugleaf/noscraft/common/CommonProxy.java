package drsmugleaf.noscraft.common;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.GuiExpanded;
import drsmugleaf.noscraft.client.gui.GuiSkillMenu;
import drsmugleaf.noscraft.common.block.ModBlocks;
import drsmugleaf.noscraft.common.container.noscraft.ContainerExpanded;
import drsmugleaf.noscraft.common.container.skill.ContainerSkillMenu;
import drsmugleaf.noscraft.common.entity.ModEntities;
import drsmugleaf.noscraft.common.event.EventHandlerEntity;
import drsmugleaf.noscraft.common.event.EventHandlerItem;
import drsmugleaf.noscraft.common.item.ModItems;
import drsmugleaf.noscraft.common.item.equipment.fairy.ModFairies;
import drsmugleaf.noscraft.common.skills.ModSkills;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class CommonProxy implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiExpanded.ID:
                return new ContainerExpanded(player);
            case GuiSkillMenu.ID:
                return new ContainerSkillMenu(player);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public void preInit(FMLPreInitializationEvent event) {}

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(EventHandlerEntity.class);
        MinecraftForge.EVENT_BUS.register(EventHandlerItem.class);
//        MinecraftForge.EVENT_BUS.register(ModArmors.class);
//        MinecraftForge.EVENT_BUS.register(ModWeapons.class);
        MinecraftForge.EVENT_BUS.register(ModFairies.class);
        MinecraftForge.EVENT_BUS.register(ModBlocks.class);
        MinecraftForge.EVENT_BUS.register(ModItems.class);
        MinecraftForge.EVENT_BUS.register(ModEntities.class);
        MinecraftForge.EVENT_BUS.register(ModSkills.class);
    }

    public final boolean isClient() {
        return !isServer();
    }

    public boolean isServer() {
        return true;
    }

    public @Nullable World getClientWorld() {
        return null;
    }

}
