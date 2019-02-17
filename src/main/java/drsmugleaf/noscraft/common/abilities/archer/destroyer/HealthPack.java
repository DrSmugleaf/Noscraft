package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;
import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class HealthPack extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 14;
    }

    @Nonnull
    @Override
    public Elements getElement() {
        return Elements.NONE;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "Removes bad effects Lv. 3 or lower";
    }

    @Override
    public double getCastTime() {
        return 0.4;
    }

    @Override
    public int getDistance() {
        return 3;
    }

    @Override
    public double getCooldown() {
        return 100;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.SELECTED;
    }

    @Override
    public int getMPCost() {
        return 85;
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
