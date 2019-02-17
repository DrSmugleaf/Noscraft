package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.IAbility;
import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public abstract class AbilityDestroyer implements IAbility {

    @Override
    public @Nonnull Elements getElement() {
        return Elements.FIRE;
    }

    @Override
    public int getCP() {
        return 0;
    }

    @Override
    public int getDistance() {
        return 6;
    }

}
