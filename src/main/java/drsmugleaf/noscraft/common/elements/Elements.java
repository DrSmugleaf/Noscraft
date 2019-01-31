package drsmugleaf.noscraft.common.elements;

import com.sun.istack.internal.NotNull;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
public enum Elements {

    NONE(Elements.NONE, Elements.NONE, Elements.NONE) {
        @Override
        public double damageMultiplierTo(Elements element) {
            return 1.0;
        }
    },
    DARKNESS(Elements.LIGHT, Elements.WATER, Elements.FIRE),
    FIRE(Elements.WATER, Elements.DARKNESS, Elements.LIGHT),
    LIGHT(Elements.DARKNESS, Elements.FIRE, Elements.WATER),
    WATER(Elements.FIRE, Elements.LIGHT, Elements.DARKNESS);

    private final @NotNull Elements OPPOSITE;
    private final @NotNull Elements STRONG_TO;
    private final @NotNull Elements WEAK_TO;

    Elements(@NotNull Elements opposite, @NotNull Elements strongTo, @NotNull Elements weakTo) {
        OPPOSITE = opposite;
        STRONG_TO = strongTo;
        WEAK_TO = weakTo;
    }

    public @NotNull Elements getOpposite() {
        return OPPOSITE;
    }

    public @NotNull Elements getStrongTo() {
        return STRONG_TO;
    }

    public @NotNull Elements getWeakTo() {
        return WEAK_TO;
    }

    public double damageMultiplierTo(@NotNull Elements element) {
        if (element == OPPOSITE) {
            return 2.0;
        } else if (element == STRONG_TO) {
            return 1.5;
        } else if (element == WEAK_TO) {
            return 1.0;
        } else if (element == NONE) {
            return 1.3;
        }

        throw new IllegalArgumentException("No damage multiplier found for " + element);
    }

}
