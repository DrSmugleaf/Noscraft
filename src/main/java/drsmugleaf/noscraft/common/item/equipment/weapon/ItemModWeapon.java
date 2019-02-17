package drsmugleaf.noscraft.common.item.equipment.weapon;

import drsmugleaf.noscraft.common.classes.Classes;
import drsmugleaf.noscraft.common.item.ItemMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 09/02/2019
 */
public class ItemModWeapon extends ItemMod {

    private final @Nonnull Classes CLASS;
    private final @Nonnull WeaponSlot SLOT;

    public ItemModWeapon(@Nonnull String name, @Nonnull Classes clazz, @Nonnull WeaponSlot slot) {
        super(name);
        CLASS = clazz;
        SLOT = slot;
        setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public @Nonnull String getLayer0Path() {
        return LAYER0_PREFIX + "weapon/" + SLOT.name().toLowerCase() + "/" + CLASS.name().toLowerCase() + "/" + toRegistryName();
    }

    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return getName();
    }

}
