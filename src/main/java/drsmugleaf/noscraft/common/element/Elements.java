package drsmugleaf.noscraft.common.element;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
public enum Elements {

    NONE(0xB9BA89) {
        @Nonnull
        @Override
        public Elements getOpposite() {
            return NONE;
        }

        @Nonnull
        @Override
        public Elements getStrongTo() {
            return NONE;
        }

        @Nonnull
        @Override
        public Elements getWeakTo() {
            return NONE;
        }

        @Override
        public double damageMultiplierTo(@Nonnull Elements element) {
            return 1.0;
        }
    },
    DARKNESS(0x222222) {
        @Nonnull
        @Override
        public Elements getOpposite() {
            return LIGHT;
        }

        @Nonnull
        @Override
        public Elements getStrongTo() {
            return WATER;
        }

        @Nonnull
        @Override
        public Elements getWeakTo() {
            return FIRE;
        }
    },
    FIRE(0xFF0000) {
        @Nonnull
        @Override
        public Elements getOpposite() {
            return WATER;
        }

        @Nonnull
        @Override
        public Elements getStrongTo() {
            return DARKNESS;
        }

        @Nonnull
        @Override
        public Elements getWeakTo() {
            return LIGHT;
        }
    },
    LIGHT(0xF5F911) {
        @Nonnull
        @Override
        public Elements getOpposite() {
            return DARKNESS;
        }

        @Nonnull
        @Override
        public Elements getStrongTo() {
            return FIRE;
        }

        @Nonnull
        @Override
        public Elements getWeakTo() {
            return WATER;
        }
    },
    WATER(0x0080FF) {
        @Nonnull
        @Override
        public Elements getOpposite() {
            return FIRE;
        }

        @Nonnull
        @Override
        public Elements getStrongTo() {
            return LIGHT;
        }

        @Nonnull
        @Override
        public Elements getWeakTo() {
            return DARKNESS;
        }
    };

    private final int HEX_COLOR;

    Elements(int hexColor) {
        HEX_COLOR = hexColor;
    }

    public int getHexColor() {
        return HEX_COLOR;
    }

    public abstract @Nonnull Elements getOpposite();

    public abstract @Nonnull Elements getStrongTo();

    public abstract @Nonnull Elements getWeakTo();

    public double damageMultiplierFrom(@Nonnull Elements element) {
        return element.damageMultiplierTo(this);
    }

    public double damageMultiplierTo(@Nonnull Elements element) {
        if (element == getOpposite()) {
            return 2.0;
        } else if (element == getStrongTo()) {
            return 1.5;
        } else if (element == getWeakTo()) {
            return 1.0;
        } else if (element == NONE) {
            return 1.3;
        }

        throw new IllegalArgumentException("No damage multiplier found for " + element);
    }

}