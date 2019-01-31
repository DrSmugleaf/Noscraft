package drsmugleaf.noscraft.common.classes;

import com.sun.istack.internal.NotNull;
import drsmugleaf.noscraft.common.elements.Elements;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
public enum SpecialistCards {

    WARRIOR(36, 20, Elements.FIRE),
    BLADE(46, 35, Elements.WATER),
    CRUSADER(55, 50, Elements.LIGHT),
    BERSERKER(65, 55, Elements.DARKNESS),
    GLADIATOR(75, 60, Elements.FIRE),
    BATTLE_MONK(80, 70, Elements.WATER),
    DEATH_REAPER(80, 70, Elements.DARKNESS),
    RENEGADE(70, 70, Elements.LIGHT), // TODO: Switch to darkness

    RED_MAGICIAN(36, 20, Elements.FIRE),
    HOLY_MAGE(46, 35, Elements.LIGHT),
    BLUE_MAGICIAN(55, 50, Elements.WATER),
    DARK_GUNNER(65, 55, Elements.DARKNESS),
    VOLCANO(75, 60, Elements.FIRE),
    TIDE_LORD(80, 70, Elements.WATER),
    SEER(80, 70, Elements.DARKNESS),
    ARCHMAGE(70, 70, Elements.LIGHT),

    RANGER(36, 20, Elements.WATER),
    ASSASSIN(46, 35, Elements.DARKNESS),
    DESTROYER(55, 50, Elements.FIRE),
    WILDKEEPER(65, 55, Elements.LIGHT),
    CANNONNER(75, 60, Elements.FIRE),
    SCOUT(80, 70, Elements.WATER),
    DEATH_HUNTER(80, 70, Elements.DARKNESS),
    AVENGING_ANGEL(70, 70, Elements.LIGHT),

    DRAGON_FIST(81, 20, Elements.FIRE),

    PAJAMA(27, 10, Elements.NONE),
    JAJAMARU(45, 38, Elements.FIRE),
    CHICKEN_COSTUME(0, 1, Elements.NONE),
    PIRATE(0, 10, Elements.NONE);

//    private final Classes[] CLASSES; // TODO: Add class restriction
    private final int LVL_OBTAINED;
    private final int JOB_LEVEL_REQUIRED;
    private final @NotNull Elements ELEMENT;

    SpecialistCards(int lvlObtained, int jobLevelRequired, @NotNull Elements element) {
        LVL_OBTAINED = lvlObtained;
        JOB_LEVEL_REQUIRED = jobLevelRequired;
        ELEMENT = element;
    }

//    public Classes[] getClasses() {
//        return CLASSES.clone();
//    }

    public int getLvlObtained() {
        return LVL_OBTAINED;
    }

    public int getJobLevelRequired() {
        return JOB_LEVEL_REQUIRED;
    }

    public @NotNull Elements getElement() {
        return ELEMENT;
    }

//    public boolean canEquip(@NotNull Classes clazz) {}

}
