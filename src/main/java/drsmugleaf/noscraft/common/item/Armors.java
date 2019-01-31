package drsmugleaf.noscraft.common.item;
import com.sun.istack.internal.NotNull;
import drsmugleaf.noscraft.common.item.Effects;
import drsmugleaf.noscraft.common.item.ArmorTypes;

public enum Armors {
    ADVENTURER_1(12, 14, 12, 24, 1, Effects.NONE, ArmorTypes.ADVENTURER_ARMOR);
    Armors(@NotNull Integer MEELEEDEF, @NotNull Integer RANGEDEF, @NotNull Integer MAGICDEF, @NotNull Integer DODGE, @NotNull Integer LEVEL, Effects EFFECT, ArmorTypes TYPE) {
        
    }
}
