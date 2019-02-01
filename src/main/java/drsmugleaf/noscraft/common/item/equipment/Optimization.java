package drsmugleaf.noscraft.common.item.equipment;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 01/02/2019
 **/
public enum Optimization {
    OPTIMIZATION_0(0),
    OPTIMIZATION_1(1),
    OPTIMIZATION_2(2),
    OPTIMIZATION_3(3),
    OPTIMIZATION_4(4),
    OPTIMIZATION_5(5),
    OPTIMIZATION_6(6),
    OPTIMIZATION_7(7),
    OPTIMIZATION_8(8),
    OPTIMIZATION_9(9),
    OPTIMIZATION_10(10);

    private final @Nonnull Integer LEVEL;
    private final @Nonnull Double EXTRASTATS;

    Optimization(@Nonnull Integer level) {
        LEVEL = level;
        if (level == 1) {
            EXTRASTATS = 1.1;
        } else if (level == 2) {
            EXTRASTATS = 1.15;
        } else if (level == 3) {
            EXTRASTATS = 1.22;
        } else if (level == 4) {
            EXTRASTATS = 1.32;
        } else if (level == 5) {
            EXTRASTATS = 1.42;
        } else if (level == 6) {
            EXTRASTATS = 1.54;
        } else if (level == 7) {
            EXTRASTATS = 1.65;
        } else if (level == 8) {
            EXTRASTATS = 1.90;
        } else if (level == 9) {
            EXTRASTATS = 2.2;
        } else if (level == 10) {
            EXTRASTATS = 3.0;
        } else {EXTRASTATS = 1.0;}
    }

    public double getExtraStats() {
        return EXTRASTATS;
    }

    public int getOptimizationLevel() {
        return LEVEL;
    }
}
