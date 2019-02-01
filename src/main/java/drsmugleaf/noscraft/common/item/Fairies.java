package drsmugleaf.noscraft.common.item;
import drsmugleaf.noscraft.common.elements.Elements;
import javax.annotation.Nonnull;
/**
 * Created by Josde on 01/02/2019
 **/
public enum Fairies {
    BASIC_FIRE_FAIRY(Elements.FIRE, 1),
    BASIC_WATER_FAIRY(Elements.WATER, 1),
    BASIC_LIGHT_FAIRY(Elements.LIGHT, 1),
    BASIC_DARKNESS_FAIRY(Elements.DARKNESS, 1),
    BASIC_NONE_FAIRY(Elements.NONE, 1);

    private final @Nonnull Integer LEVEL;
    private final @Nonnull Elements ELEMENT;
    private  @Nonnull Double DAMAGEMULT;

    Fairies(Elements element, @Nonnull Integer level) {
        LEVEL = level;
        ELEMENT = element;
    }

    public int getLevel() {return LEVEL;}
    public Elements getElement() {return ELEMENT;}
    public double getFairyDamageAgainst(Elements attackedElement) {
        DAMAGEMULT = ELEMENT.damageMultiplierTo(attackedElement)  * ((float)LEVEL / 100);
        return DAMAGEMULT;
    }

}
