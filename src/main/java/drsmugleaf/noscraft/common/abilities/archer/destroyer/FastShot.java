package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public class FastShot extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 2;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "Ranged attack +300, fire element +350, 30% chance to cast blackout: (Disabled movement and attacks for 5 seconds)";
    }

    @Override
    public double getCastTime() {
        return 0.2;
    }

    @Override
    public double getCooldown() {
        return 7;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.SELECTED;
    }

    @Override
    public int getMPCost() {
        return 45;
    }

    @Override
    public int getDamageBonus() {
        return 300;
    }

    @Override
    public int getElementBonus() {
        return 350;
    }

}
