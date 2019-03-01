package drsmugleaf.noscraft.common.item.equipment.fairy;

import drsmugleaf.noscraft.common.element.Elements;
import drsmugleaf.noscraft.common.item.ItemMod;
import drsmugleaf.noscraft.common.item.creativetab.CreativeTabFairy;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class ItemFairy extends ItemMod implements IFairy {

    private final @Nonnull Fairies FAIRY;

    public ItemFairy(@Nonnull Fairies fairy) {
        super(fairy.getName(), fairy.getName());
        FAIRY = fairy;
        setCreativeTab(CreativeTabFairy.INSTANCE);
        maxStackSize = 1;
    }

    @Override
    public @Nonnull String getLayer0Path() {
        return LAYER0_PREFIX + "fairy/" + toRegistryName();
    }

    public @Nonnull Fairies getFairy() {
        return FAIRY;
    }

    @Override
    public @Nonnull String getItemStackDisplayName(@Nonnull ItemStack stack) {
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
