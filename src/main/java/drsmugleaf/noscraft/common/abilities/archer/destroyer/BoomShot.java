package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class BoomShot extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 16;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "Ranged attack +900, fire element +1100, 40% chance to cast serious bleeding: [Lose HP every 2 seconds (Caster Lv. *4) for 20 seconds, 30% chance of casting broken heart disease: (Lose MP every 4 seconds (Caster Lv./3), movement speed -20% for 30 seconds]";
    }

    @Override
    public double getCastTime() {
        return 0.5;
    }

    @Override
    public double getCooldown() {
        return 30;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.ALL_IN_1_CELL;
    }

    @Override
    public int getMPCost() {
        return 150;
    }

    @Override
    public int getDamageBonus() {
        return 900;
    }

    @Override
    public int getElementBonus() {
        return 1100;
    }

}
