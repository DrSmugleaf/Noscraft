package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public class LuckyWideshot extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 4;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "Ranged attack +700, fire element +700, 100% chance of critical attack";
    }

    @Override
    public double getCastTime() {
        return 0.4;
    }

    @Override
    public double getCooldown() {
        return 40;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.SPECIAL_AREA;
    }

    @Override
    public int getMPCost() {
        return 70;
    }

    @Override
    public int getDamageBonus() {
        return 700;
    }

    @Override
    public int getElementBonus() {
        return 700;
    }

}
