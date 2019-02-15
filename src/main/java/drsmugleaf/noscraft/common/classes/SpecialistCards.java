package drsmugleaf.noscraft.common.classes;

import drsmugleaf.noscraft.common.elements.Elements;
import drsmugleaf.noscraft.common.item.equipment.weapon.WeaponSlot;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
public enum SpecialistCards {

    WARRIOR(36, 20, Elements.FIRE, WeaponSlot.MAIN, Classes.SWORDSMAN),
    BLADE(46, 35, Elements.WATER, WeaponSlot.MAIN, Classes.SWORDSMAN),
    CRUSADER(55, 50, Elements.LIGHT, WeaponSlot.SECONDARY, Classes.SWORDSMAN),
    BERSERKER(65, 55, Elements.DARKNESS, WeaponSlot.MAIN, Classes.SWORDSMAN),
    GLADIATOR(75, 60, Elements.FIRE, WeaponSlot.MAIN, Classes.SWORDSMAN),
    BATTLE_MONK(80, 70, Elements.WATER, WeaponSlot.MAIN, Classes.SWORDSMAN),
    DEATH_REAPER(80, 70, Elements.DARKNESS, WeaponSlot.MAIN, Classes.SWORDSMAN),
    RENEGADE(70, 70, Elements.LIGHT, WeaponSlot.MAIN, Classes.SWORDSMAN), // TODO: Switch to darkness

    RANGER(36, 20, Elements.WATER, WeaponSlot.MAIN, Classes.ARCHER),
    ASSASSIN(46, 35, Elements.DARKNESS, WeaponSlot.SECONDARY, Classes.ARCHER),
    DESTROYER(55, 50, Elements.FIRE, WeaponSlot.MAIN, Classes.ARCHER),
    WILDKEEPER(65, 55, Elements.LIGHT, WeaponSlot.MAIN, Classes.ARCHER),
    CANNONNER(75, 60, Elements.FIRE, WeaponSlot.MAIN, Classes.ARCHER),
    SCOUT(80, 70, Elements.WATER, WeaponSlot.MAIN, Classes.ARCHER),
    DEATH_HUNTER(80, 70, Elements.DARKNESS, WeaponSlot.MAIN, Classes.ARCHER),
    AVENGING_ANGEL(70, 70, Elements.LIGHT, WeaponSlot.SECONDARY, Classes.ARCHER),

    RED_MAGICIAN(36, 20, Elements.FIRE, WeaponSlot.MAIN, Classes.MAGE),
    HOLY_MAGE(46, 35, Elements.LIGHT, WeaponSlot.MAIN, Classes.MAGE),
    BLUE_MAGICIAN(55, 50, Elements.WATER, WeaponSlot.MAIN, Classes.MAGE),
    DARK_GUNNER(65, 55, Elements.DARKNESS, WeaponSlot.SECONDARY, Classes.MAGE),
    VOLCANO(75, 60, Elements.FIRE, WeaponSlot.MAIN, Classes.MAGE),
    TIDE_LORD(80, 70, Elements.WATER, WeaponSlot.MAIN, Classes.MAGE),
    SEER(80, 70, Elements.DARKNESS, WeaponSlot.MAIN, Classes.MAGE),
    ARCHMAGE(70, 70, Elements.LIGHT, WeaponSlot.MAIN, Classes.MAGE),

    DRAGON_FIST(81, 20, Elements.FIRE, WeaponSlot.MAIN, Classes.MARTIAL_ARTIST),
    MYSTIC_ART(81, 20, Elements.WATER, WeaponSlot.MAIN, Classes.MARTIAL_ARTIST),

    PAJAMA(27, 10, Elements.NONE, WeaponSlot.MAIN, Classes.values()),
    JAJAMARU(45, 38, Elements.FIRE, WeaponSlot.MAIN, Classes.SWORDSMAN, Classes.ARCHER, Classes.MAGE), // TODO: Add Martial artist to valid classes
    CHICKEN_COSTUME(0, 1, Elements.NONE, WeaponSlot.MAIN, Classes.values()),
    PIRATE(0, 10, Elements.NONE, WeaponSlot.MAIN, Classes.values());

    private final int LVL_OBTAINED;
    private final int JOB_LEVEL_REQUIRED;
    private final @Nonnull Elements ELEMENT;
    private final @Nonnull Classes[] CLASSES;
    private final @Nonnull WeaponSlot WEAPON;

    SpecialistCards(int lvlObtained, int jobLevelRequired, @Nonnull Elements element, @Nonnull WeaponSlot weapon, @Nonnull Classes... classes) {
        LVL_OBTAINED = lvlObtained;
        JOB_LEVEL_REQUIRED = jobLevelRequired;
        ELEMENT = element;
        CLASSES = classes;
        WEAPON = weapon;
    }
    public WeaponSlot getWeaponSlot() { return WEAPON; }


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
