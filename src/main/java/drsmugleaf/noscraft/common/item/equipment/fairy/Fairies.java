package drsmugleaf.noscraft.common.item.equipment.fairy;

import drsmugleaf.noscraft.common.elements.Elements;
import javax.annotation.Nonnull;

/**
 * Created by Josde on 01/02/2019
 **/
public enum Fairies {

    FIRE_FAIRY("Fire Fairy", 0, 50, 80, Elements.FIRE),
    WATER_FAIRY("Water Fairy", 0, 50, 80, Elements.WATER),
    LIGHT_FAIRY("Light Fairy", 0, 50, 80, Elements.LIGHT),
    DARKNESS_FAIRY("Darkness Fairy", 0, 50, 80, Elements.DARKNESS),

    SELLAIM("Sellaim", 0, 70, 100, Elements.FIRE),
    WOONDINE("Woondine", 0, 70, 100, Elements.WATER),
    EPERIAL("Eperial", 0, 70, 100, Elements.LIGHT),
    TURIK("Turik", 0, 70, 100, Elements.DARKNESS),

    SELLAIM_GRANDE("Sellaim Grande", 40, 70, 100, Elements.FIRE),
    WOONDINE_GRANDE("Woondine Grande", 40, 70, 100, Elements.WATER),
    EPERIAL_GRANDE("Eperial Grande", 40, 70, 100, Elements.LIGHT),
    TURIK_GRANDE("Turik Grande", 40, 70, 100, Elements.DARKNESS),

    ELKAIM("Elkaim", 50, 80, 110, Elements.FIRE),
    LADINE("Ladine", 50, 80, 110, Elements.WATER),
    RUMIAL("Rumial", 50, 80, 110, Elements.LIGHT),
    VARIK("Varik", 50, 80, 110, Elements.DARKNESS),

    CHICKY_FAIRY("Chicky Fairy", 0, 0, 0, Elements.NONE);
    
    private final @Nonnull String NAME;
    private final int STARTING_LEVEL;
    private final int MAX_LEVEL;
    private final int AMPLIFIED_LEVEL;
    private final @Nonnull Elements ELEMENT;

    Fairies(String name, int startingLevel, int maxLevel, int amplifiedLevel, @Nonnull Elements element) {
        NAME = name;
        STARTING_LEVEL = startingLevel;
        MAX_LEVEL = maxLevel;
        AMPLIFIED_LEVEL = amplifiedLevel;
        ELEMENT = element;
    }

    public @Nonnull String getName() {
        return NAME;
    }

    public int getStartingLevel() {
        return STARTING_LEVEL;
    }

    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    public int getAmplifiedLevel() {
        return AMPLIFIED_LEVEL;
    }

    public @Nonnull Elements getElement() {
        return ELEMENT;
    }

    public double getFairyDamageAgainst(@Nonnull Elements attackedElement) {
        return ELEMENT.damageMultiplierTo(attackedElement) * ((float) STARTING_LEVEL / 100);
    }

}
