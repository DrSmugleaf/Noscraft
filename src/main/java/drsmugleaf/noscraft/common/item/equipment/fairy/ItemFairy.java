package drsmugleaf.noscraft.common.item.equipment.fairy;

import drsmugleaf.noscraft.common.elements.Elements;
import drsmugleaf.noscraft.common.item.ItemMod;
import drsmugleaf.noscraft.common.item.NoscraftFairyTab;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class ItemFairy extends ItemMod implements IFairy {

    private final @Nonnull Fairies FAIRY;

    public ItemFairy(@Nonnull Fairies fairy) {
        super(fairy.getName());
        FAIRY = fairy;
        setCreativeTab(NoscraftFairyTab.INSTANCE);
        maxStackSize = 1;
    }

    public @Nonnull Fairies getFairy() {
        return FAIRY;
    }

    @Override
    public @Nonnull String getItemStackDisplayName(ItemStack stack) {
        return FAIRY.getName();
    }

    @Override
    public int getLevel() {
        return FAIRY.getStartingLevel();
    }

    @Override
    public @Nonnull Elements getElement() {
        return FAIRY.getElement();
    }

}