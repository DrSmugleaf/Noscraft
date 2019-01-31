package drsmugleaf.noscraft.common.classes;
import com.sun.istack.internal.NotNull;
import drsmugleaf.noscraft.common.item.Weapons;
import drsmugleaf.noscraft.common.item.ArmorTypes;
import drsmugleaf.noscraft.common.item.WeaponTypes;
public enum Classes {
    /**
     * Created by Josde on 31/01/2019
     */
    ADVENTURER(WeaponTypes.ADVENTURER_MAIN, WeaponTypes.ADVENTURER_SECONDARY, ArmorTypes.ADVENTURER_ARMOR),
    SWORDSMAN(WeaponTypes.SWORD, WeaponTypes.CROSSBOW, ArmorTypes.SWORDSMAN_ARMOR),
    ARCHER(WeaponTypes.BOW, WeaponTypes.DAGGER, ArmorTypes.ARCHER_ARMOR),
    MAGE(WeaponTypes.STAFF, WeaponTypes.GUN, ArmorTypes.MAGE_ARMOR);

    Classes(@NotNull WeaponTypes MAIN_WEAPONS, @NotNull WeaponTypes SECONDARY_WEAPONS, @NotNull ArmorTypes ARMORS) {

    }
}
