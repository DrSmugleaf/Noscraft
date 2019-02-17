package drsmugleaf.noscraft.common.item.equipment;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Josde on 01/02/2019
 **/
public enum Optimization {

    ZERO(0, 1, 0, 0, 1),
    ONE(1, 1, 0, 0, 1.1),
    TWO(2, 0.9, 0.1, 0, 1.15),
    THREE(3, 0.8, 0.15, 0.05, 1.22),
    FOUR(4, 0.6, 0.2, 0.2, 1.32),
    FIVE(5, 0.4, 0.2, 0.4, 1.42),
    SIX(6, 0.2, 0.2, 0.6, 1.54),
    SEVEN(7, 0.05, 0.2, 0.75, 1.65),
    EIGHT(8, 0.02, 0.2, 0.78, 1.90),
    NINE(9, 0.01, 0.2, 0.79, 2.2),
    TEN(10, 0, 1, 0, 3);

    private final int LEVEL;
    private final double OPTIMIZATION_CHANCE;
    private final double LOCK_CHANCE;
    private final double BREAK_CHANCE;
    private final double EXTRA_STATS;

    Optimization(int level, double optimizationChance, double lockChance, double breakChance, double extraStats) {
        LEVEL = level;
        OPTIMIZATION_CHANCE = optimizationChance;
        LOCK_CHANCE = lockChance;
        BREAK_CHANCE = breakChance;
        EXTRA_STATS = extraStats;
    }

    public int getOptimizationLevel() {
        return LEVEL;
    }

    public double getOptimizationChance() {
        return OPTIMIZATION_CHANCE;
    }

    public double getLockChance() {
        return LOCK_CHANCE;
    }

    public double getBreakChance() {
        return BREAK_CHANCE;
    }

    public double getExtraStats() {
        return EXTRA_STATS;
    }

    public int optimizeItem() {
        double random = ThreadLocalRandom.current().nextDouble();

        if (random < getOptimizationChance()) {
            return getOptimizationLevel() + 1;
        } else if (random >= getOptimizationChance() && random > getBreakChance()){
            // TODO: 01/02/2019 LOCK THE ITEM
            return getOptimizationLevel();
        } else {
            // TODO: 01/02/2019 BREAK THE ITEM
            return 0;
        }
    }

}
