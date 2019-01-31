package drsmugleaf.noscraft.common.item;
import com.sun.istack.internal.NotNull;
import drsmugleaf.noscraft.common.item.Effects;
import drsmugleaf.noscraft.common.item.WeaponTypes;
/**
 * Created by Josde on 31/01/2019
 */
public enum Weapons {
    ADVENTURER_MAIN_1(24, 20, 4, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_MAIN),
    ADVENTURER_SECONDARY_1(23, 21, 2, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_SECONDARY);
    private final @NotNull Integer RANGE;
    Weapons(@NotNull Integer DAMAGE, int HITRATE, int CRITCHANCE, @NotNull Integer CRITMULTIPLIER, Integer LEVEL, Effects EFFECT, WeaponTypes TYPE) {
        if (TYPE == WeaponTypes.ADVENTURER_MAIN || TYPE == WeaponTypes.DAGGER || TYPE == WeaponTypes.SWORD) {
            RANGE = 1;
        }
        else {
            RANGE = 4;
        }

    }
}



