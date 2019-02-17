package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public class Claymore extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 8;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "Set up a Claymore at your feet";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "";
    }

    @Override
    public double getCastTime() {
        return 1.0;
    }

    @Override
    public int getDistance() {
        return 0;
    }

    @Override
    public double getCooldown() {
        return 30;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.ALL_IN_3_CELLS;
    }

    @Override
    public int getMPCost() {
        return 50;
    }

    @Override
    public int getDamageBonus() {
        return 0;
    }

    @Override
    public int getElementBonus() {
        return 0;
    }

}
