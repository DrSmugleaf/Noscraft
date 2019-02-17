package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class HellDrop extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 20;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "Ranged attack +1000, fire element +1500, 80% chance to cast deadly blackout: (Disables movement and attack for 6 seconds, dodge reduced to 0 for .5 seconds)";
    }

    @Override
    public double getCastTime() {
        return 1.2;
    }

    @Override
    public double getCooldown() {
        return 120;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.ALL_IN_2_CELLS;
    }

    @Override
    public int getMPCost() {
        return 200;
    }

    @Override
    public int getDamageBonus() {
        return 1000;
    }

    @Override
    public int getElementBonus() {
        return 1500;
    }

}
