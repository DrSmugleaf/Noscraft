package drsmugleaf.noscraft.common.item.creativetab;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 28/02/2019
 */
public class CreativeTabSwordsman extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":swordsman.sword_for_beginners")
    @Nonnull
    private static final Item ICON = null;

    @Nonnull
    public static final CreativeTabSwordsman INSTANCE = new CreativeTabSwordsman();

    private CreativeTabSwordsman() {
        super("swordsman");
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ICON);
    }

}
