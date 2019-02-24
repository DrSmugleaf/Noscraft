package drsmugleaf.noscraft.client;

import com.google.common.collect.ImmutableMap;
import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.GuiExpanded;
import drsmugleaf.noscraft.client.gui.GuiSkillMenu;
import drsmugleaf.noscraft.client.gui.ModGuis;
import drsmugleaf.noscraft.client.keybinding.ModKeys;
import drsmugleaf.noscraft.client.render.entity.RenderNuke;
import drsmugleaf.noscraft.client.render.entity.RenderNukeCircle;
import drsmugleaf.noscraft.common.CommonProxy;
import drsmugleaf.noscraft.common.entity.EntityNuke;
import drsmugleaf.noscraft.common.entity.EntityNukeCircle;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Nonnull
    public static ImmutableMap<String, TextureAtlasSprite> getTextures(@Nonnull OBJModel model) {
        ImmutableMap.Builder<String, TextureAtlasSprite> builder = ImmutableMap.builder();
        builder.put(ModelLoader.White.LOCATION.toString(), ModelLoader.White.INSTANCE);
        TextureAtlasSprite missing = ModelLoader.defaultTextureGetter().apply(new ResourceLocation("missingno"));
        for (String materialName : model.getMatLib().getMaterialNames()) {
            OBJModel.Material material = model.getMatLib().getMaterial(materialName);
            if (material.getTexture().getTextureLocation().getResourcePath().startsWith("#")) {
                FMLLog.bigWarning("OBJLoaderMW: Unresolved texture '%s' for obj model '%s'", material.getTexture().getTextureLocation().getResourcePath(), model.toString());
                builder.put(materialName, missing);
            }
            else
                builder.put(materialName, ModelLoader.defaultTextureGetter().apply(material.getTexture().getTextureLocation()));
        }

        builder.put("missingno", missing);

        return builder.build();
    }

    @Override
    public @Nullable Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            switch (ID) {
                case GuiExpanded.ID:
                    return new GuiExpanded(player);
                case GuiSkillMenu.ID:
                    return new GuiSkillMenu(player);
            }
        }

        return null;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        ModKeys.init();
        OBJLoader.INSTANCE.addDomain(Noscraft.MOD_ID);
        RenderingRegistry.registerEntityRenderingHandler(EntityNuke.class, RenderNuke::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNukeCircle.class, RenderNukeCircle::new);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();

        MinecraftForge.EVENT_BUS.register(ModGuis.class);
        MinecraftForge.EVENT_BUS.register(ModKeys.class);
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public @Nonnull World getClientWorld() {
        return FMLClientHandler.instance().getClient().world;
    }

}
