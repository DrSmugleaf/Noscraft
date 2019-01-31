package drsmugleaf.noscraft.common.item;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Armors {

    ADVENTURER_1(12, 14, 12, 24, 1, Effects.NONE, ArmorTypes.ADVENTURER_ARMOR);

    Armors(
            @Nonnull Integer meleeDef,
            @Nonnull Integer rangeDef,
            @Nonnull Integer magicDef,
            @Nonnull Integer dodge,
            @Nonnull Integer level,
            @Nonnull Effects effect,
            @Nonnull ArmorTypes type
    ) {
        
    }

}
