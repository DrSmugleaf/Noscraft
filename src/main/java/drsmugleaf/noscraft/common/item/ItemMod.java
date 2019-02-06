package drsmugleaf.noscraft.common.item;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class ItemMod extends Item {

    public ItemMod(@Nonnull String name) {
        super();
        name = toRegistryName(name);
        setRegistryName(new ResourceLocation(Noscraft.MOD_ID, name));
        setUnlocalizedName(getRegistryName().toString());

        if (!Noscraft.getProxy().isServer()) {
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("noscraft:" + name, "inventory"));
        }
    }

    @Nonnull
    public static String toRegistryName(@Nonnull String name) {
        return name
                .replace(' ', '_')
                .replaceAll("[':]", "")
                .toLowerCase();
    }

}