package drsmugleaf.noscraft.common.item.equipment.armor;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.IModellable;
import drsmugleaf.noscraft.common.IRegistrable;
import drsmugleaf.noscraft.common.classes.Classes;
import drsmugleaf.noscraft.common.classes.IClassSpecific;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ItemModArmor extends ItemArmor implements IModellable, IClassSpecific {

    private final @Nonnull String NAME;
    private final @Nonnull Classes CLASS;

    public ItemModArmor(@Nonnull EntityEquipmentSlot slot, @Nonnull String name, @Nonnull Classes clazz) {
        super(ArmorMaterial.LEATHER, 0, slot);
        NAME = name;
        CLASS = clazz;
        setCreativeTab(CreativeTabs.COMBAT);

        register(this);
        if (Noscraft.getProxy().isClient()) {
            ModelLoader.setCustomModelResourceLocation(this, 0, getModelResourceLocation());
        }
    }

    @Override
    public @Nonnull String getLayer0Path() {
        return LAYER0_PREFIX + "armor/" + CLASS.name().toLowerCase() + "/" + IRegistrable.toRegistryName(NAME);
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return NAME;
    }

    @Nonnull
    @Override
    public Classes getUsableBy() {
        return CLASS;
    }

    @Nonnull
    @Override
    public String getNameToRegister() {
        return CLASS.name() + "." + NAME;
    }

}
