package drsmugleaf.noscraft.common.item;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Weapons {

    ADVENTURER_MAIN_1(24, 20, 4, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_MAIN),
    ADVENTURER_SECONDARY_1(23, 21, 2, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_SECONDARY);

    private final @Nonnull Integer RANGE;

    Weapons(int damage, int hitRate, int critChance, int critMultiplier, int level, @Nonnull Effects effect, @Nonnull WeaponTypes type) {
        if (type == WeaponTypes.ADVENTURER_MAIN || type == WeaponTypes.DAGGER || type == WeaponTypes.SWORD) {
            RANGE = 1;
        }
        else {
            RANGE = 4;
        }
    }

}



