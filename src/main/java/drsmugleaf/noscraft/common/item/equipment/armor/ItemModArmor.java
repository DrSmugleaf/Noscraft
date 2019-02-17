package drsmugleaf.noscraft.common.item.equipment.armor;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.IModellable;
import drsmugleaf.noscraft.common.classes.Classes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ItemModArmor extends ItemArmor implements IModellable {

    private final @Nonnull String NAME;
    private final @Nonnull Classes CLASS;

    public ItemModArmor(@Nonnull EntityEquipmentSlot slot, @Nonnull String name, @Nonnull Classes clazz) {
        super(ArmorMaterial.LEATHER, 0, slot);
        NAME = name;
        name = register(this);
        CLASS = clazz;
        setCreativeTab(CreativeTabs.COMBAT);

        if (Noscraft.getProxy().isClient()) {
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("noscraft:" + name, "inventory"));
        }
    }

    @Override
    public @Nonnull String getLayer0Path() {
        return LAYER0_PREFIX + "armor/" + CLASS.name().toLowerCase() + "/" + toRegistryName();
    }

    @Override
    public @Nonnull String getName() {
        return NAME;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return NAME;
    }

}
