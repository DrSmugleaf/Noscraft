package drsmugleaf.noscraft.common.item.equipment.fairy;

import drsmugleaf.noscraft.common.elements.Elements;
import javax.annotation.Nonnull;

/**
 * Created by Josde on 01/02/2019
 **/
public enum Fairies {

    BASIC_FIRE_FAIRY(1, Elements.FIRE),
    BASIC_WATER_FAIRY(1, Elements.WATER),
    BASIC_LIGHT_FAIRY(1, Elements.LIGHT),
    BASIC_DARKNESS_FAIRY(1, Elements.DARKNESS),
    BASIC_NONE_FAIRY(1, Elements.NONE);

    private final int STARTING_LEVEL;
    private final @Nonnull Elements ELEMENT;

    Fairies(int startingLevel, @Nonnull Elements element) {
        STARTING_LEVEL = startingLevel;
        ELEMENT = element;
    }

    public int getLevel() {
        return STARTING_LEVEL;
    }

    public @Nonnull Elements getElement() {
        return ELEMENT;
    }

    public double getFairyDamageAgainst(@Nonnull Elements attackedElement) {
        return ELEMENT.damageMultiplierTo(attackedElement) * ((float) STARTING_LEVEL / 100);
    }

}
