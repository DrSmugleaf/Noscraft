package drsmugleaf.noscraft.common.item.equipment.fairy;

import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class FairyItem implements IFairy {

    public FairyItem() {}

    @Override
    public int getLevel() {
        return 0;
    }

    @Nonnull
    @Override
    public Elements getElement() {
        return Elements.NONE;
    }

}
