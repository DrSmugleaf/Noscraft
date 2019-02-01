package drsmugleaf.noscraft.common.item;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Weapons {

    ADVENTURER_MAIN_1(24, 20, 4, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_MAIN),
    ADVENTURER_SECONDARY_1(23, 21, 2, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_SECONDARY);

    private final @Nonnull Integer RANGE;

    Weapons(@Nonnull Integer damage,
            @Nonnull Integer hitRate,
            @Nonnull Integer critChance,
            @Nonnull Integer critMultiplier,
            @Nonnull Integer level,
            @Nonnull Effects effect,
            @Nonnull WeaponTypes type) {
        RANGE = type.getRange();
    }

}



