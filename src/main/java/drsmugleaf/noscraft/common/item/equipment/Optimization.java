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
    private final @Nonnull Double OPTIMIZATIONCHANCE;
    private final @Nonnull Double LOCKCHANCE;
    private final @Nonnull Double BREAKCHANCE;
    private final @Nonnull Double EXTRASTATS;

    Optimization(@Nonnull Integer level) {
        LEVEL = level;
        if (level == 1) {
            EXTRASTATS = 1.1;
            OPTIMIZATIONCHANCE = 1.0;
            LOCKCHANCE = BREAKCHANCE = 0.0;
        } else if (level == 2) {
            EXTRASTATS = 1.15;
            OPTIMIZATIONCHANCE = 0.9;
            LOCKCHANCE = 0.1;
            BREAKCHANCE = 0.0;
        } else if (level == 3) {
            EXTRASTATS = 1.22;
            OPTIMIZATIONCHANCE = 0.8;
            LOCKCHANCE = 0.15;
            BREAKCHANCE = 0.05;
        } else if (level == 4) {
            EXTRASTATS = 1.32;
            OPTIMIZATIONCHANCE = 0.6;
            LOCKCHANCE = 0.2;
            BREAKCHANCE = 0.2;
        } else if (level == 5) {
            EXTRASTATS = 1.42;
            OPTIMIZATIONCHANCE = 0.4;
            LOCKCHANCE = 0.2;
            BREAKCHANCE = 0.4;
        } else if (level == 6) {
            EXTRASTATS = 1.54;
            OPTIMIZATIONCHANCE = 0.2;
            LOCKCHANCE = 0.2;
            BREAKCHANCE = 0.6;
        } else if (level == 7) {
            EXTRASTATS = 1.65;
            OPTIMIZATIONCHANCE = 0.05;
            LOCKCHANCE = 0.2;
            BREAKCHANCE = 0.75;
        } else if (level == 8) {
            EXTRASTATS = 1.90;
            OPTIMIZATIONCHANCE = 0.02;
            LOCKCHANCE = 0.2;
            BREAKCHANCE = 0.78;
        } else if (level == 9) {
            EXTRASTATS = 2.2;
            OPTIMIZATIONCHANCE = 0.01;
            LOCKCHANCE = 0.2;
            BREAKCHANCE = 0.79;
        } else if (level == 10) {
            EXTRASTATS = 3.0;
            OPTIMIZATIONCHANCE = 0.0; //cannot upgrade past +10
            LOCKCHANCE = 1.0;
            BREAKCHANCE = 0.0;
        } else {
            EXTRASTATS = 1.0;
            OPTIMIZATIONCHANCE = 1.0;
            LOCKCHANCE = BREAKCHANCE = 0.0;
        }
    }

    public double getExtraStats() {
        return EXTRASTATS;
    }
    public int getOptimizationLevel() { return LEVEL; }
    public double getOptimizationChance() { return OPTIMIZATIONCHANCE; }
    public double getLockChance() { return LOCKCHANCE; }
    public double getBreakChance() { return BREAKCHANCE; }
}
