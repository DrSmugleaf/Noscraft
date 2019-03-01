package drsmugleaf.noscraft.common.classes;

import drsmugleaf.noscraft.common.skills.ISkill;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Created by DrSmugleaf on 21/02/2019
 */
public interface ITransformation extends IClassSpecific {

    @Nonnull
    String getName();

    @Nonnull
    String getFileName();

    @Nonnull
    Set<ISkill> getSkills();

}
