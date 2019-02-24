package drsmugleaf.noscraft.common.skills;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public enum Target {

    AROUND_1,
    AROUND_2,
    AROUND_3,
    SINGLE,
    SPECIAL;

    public static Target from(@Nonnull String name) {
        for (Target target : values()) {
            if (target.name().equalsIgnoreCase(name)) {
                return target;
            }
        }

        throw new IllegalArgumentException("No target enum found with name " + name);
    }

}
