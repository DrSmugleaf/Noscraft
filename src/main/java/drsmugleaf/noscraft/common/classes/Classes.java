package drsmugleaf.noscraft.common.classes;

import drsmugleaf.noscraft.common.item.equipment.armor.ArmorTypes;
import drsmugleaf.noscraft.common.item.equipment.weapon.WeaponTypes;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Classes {

    ADVENTURER(WeaponTypes.ADVENTURER_MAIN, WeaponTypes.ADVENTURER_SECONDARY, ArmorTypes.ADVENTURER_ARMOR),
    SWORDSMAN(WeaponTypes.SWORD, WeaponTypes.CROSSBOW, ArmorTypes.SWORDSMAN_ARMOR),
    ARCHER(WeaponTypes.BOW, WeaponTypes.DAGGER, ArmorTypes.ARCHER_ARMOR),
    MAGE(WeaponTypes.STAFF, WeaponTypes.GUN, ArmorTypes.MAGE_ARMOR),
    MARTIALARTIST(WeaponTypes.GAUNTLET, WeaponTypes.TOKEN, ArmorTypes.MARTIAL_ARTIST_ARMOR);

    private final @Nonnull WeaponTypes MAIN;
    private final @Nonnull WeaponTypes SECONDARY;
    private final @Nonnull ArmorTypes ARMOR;

    Classes(@Nonnull WeaponTypes main, @Nonnull WeaponTypes secondary, @Nonnull ArmorTypes armor) {
        MAIN = main;
        SECONDARY = secondary;
        ARMOR = armor;
    }

    @Nonnull
    public WeaponTypes getMain() {
        return MAIN;
    }

    @Nonnull
    public WeaponTypes getSecondary() {
        return SECONDARY;
    }

    @Nonnull
    public ArmorTypes getArmor() {
        return ARMOR;
    }

}
