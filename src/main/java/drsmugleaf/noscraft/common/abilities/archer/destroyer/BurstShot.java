package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class BurstShot extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 12;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "Ranged attack +300, fire element +1500, pushes enemy away 4 cells";
    }

    @Override
    public double getCastTime() {
        return 0.5;
    }

    @Override
    public double getCooldown() {
        return 23;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.SELECTED;
    }

    @Override
    public int getMPCost() {
        return 110;
    }

    @Override
    public int getDamageBonus() {
        return 300;
    }

    @Override
    public int getElementBonus() {
        return 1500;
    }

}
