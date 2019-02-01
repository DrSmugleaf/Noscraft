package drsmugleaf.noscraft.common.classes;

import drsmugleaf.noscraft.common.elements.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
public enum SpecialistCards {

    WARRIOR(36, 20, Elements.FIRE, Classes.SWORDSMAN),
    BLADE(46, 35, Elements.WATER, Classes.SWORDSMAN),
    CRUSADER(55, 50, Elements.LIGHT, Classes.SWORDSMAN),
    BERSERKER(65, 55, Elements.DARKNESS, Classes.SWORDSMAN),
    GLADIATOR(75, 60, Elements.FIRE, Classes.SWORDSMAN),
    BATTLE_MONK(80, 70, Elements.WATER, Classes.SWORDSMAN),
    DEATH_REAPER(80, 70, Elements.DARKNESS, Classes.SWORDSMAN),
    RENEGADE(70, 70, Elements.LIGHT, Classes.SWORDSMAN), // TODO: Switch to darkness

    RANGER(36, 20, Elements.WATER, Classes.ARCHER),
    ASSASSIN(46, 35, Elements.DARKNESS, Classes.ARCHER),
    DESTROYER(55, 50, Elements.FIRE, Classes.ARCHER),
    WILDKEEPER(65, 55, Elements.LIGHT, Classes.ARCHER),
    CANNONNER(75, 60, Elements.FIRE, Classes.ARCHER),
    SCOUT(80, 70, Elements.WATER, Classes.ARCHER),
    DEATH_HUNTER(80, 70, Elements.DARKNESS, Classes.ARCHER),
    AVENGING_ANGEL(70, 70, Elements.LIGHT, Classes.ARCHER),

    RED_MAGICIAN(36, 20, Elements.FIRE, Classes.MAGE),
    HOLY_MAGE(46, 35, Elements.LIGHT, Classes.MAGE),
    BLUE_MAGICIAN(55, 50, Elements.WATER, Classes.MAGE),
    DARK_GUNNER(65, 55, Elements.DARKNESS, Classes.MAGE),
    VOLCANO(75, 60, Elements.FIRE, Classes.MAGE),
    TIDE_LORD(80, 70, Elements.WATER, Classes.MAGE),
    SEER(80, 70, Elements.DARKNESS, Classes.MAGE),
    ARCHMAGE(70, 70, Elements.LIGHT, Classes.MAGE),

//    DRAGON_FIST(81, 20, Elements.FIRE, Classes.MARTIAL_ARTIST), TODO: Martial artist

    PAJAMA(27, 10, Elements.NONE, Classes.values()),
    JAJAMARU(45, 38, Elements.FIRE, Classes.SWORDSMAN, Classes.ARCHER, Classes.MAGE), // TODO: Add Martial artist to valid classes
    CHICKEN_COSTUME(0, 1, Elements.NONE, Classes.values()),
    PIRATE(0, 10, Elements.NONE, Classes.values());

    private final int LVL_OBTAINED;
    private final int JOB_LEVEL_REQUIRED;
    private final @Nonnull Elements ELEMENT;
    private final @Nonnull Classes[] CLASSES;

    SpecialistCards(int lvlObtained, int jobLevelRequired, @Nonnull Elements element, @Nonnull Classes... classes) {
        LVL_OBTAINED = lvlObtained;
        JOB_LEVEL_REQUIRED = jobLevelRequired;
        ELEMENT = element;
        CLASSES = classes;
    }

    public int getLvlObtained() {
        return LVL_OBTAINED;
    }

    public int getJobLevelRequired() {
        return JOB_LEVEL_REQUIRED;
    }

    public @Nonnull Elements getElement() {
        return ELEMENT;
    }

    public @Nonnull Classes[] getClasses() {
        return CLASSES.clone();
    }

    public boolean canEquip(@Nonnull Classes clazz) {
        for (Classes validClass : CLASSES) {
            if (clazz == validClass) {
                return true;
            }
        }

        return false;
    }

}
