package drsmugleaf.noscraft.common.skills;

import drsmugleaf.noscraft.common.IRegistrable;
import drsmugleaf.noscraft.common.skills.archer.destroyer.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrSmugleaf on 23/02/2019
 */
public class ModEffects {

    @Nonnull
    private static Map<String, IEffect> EFFECTS = registerEffects();

    @Nonnull
    private static Map<String, IEffect> registerEffects() {
        EFFECTS = new HashMap<>();
        register(FireTheShotgun.class);
        register(FastShot.class);
        register(LuckyWideshot.class);
        register(BoosterOn.class);
        register(Claymore.class);
        register(GasShell.class);
        register(BurstShot.class);
        register(HealthPack.class);
        register(BoomShot.class);
        register(FireMine.class);
        register(HellDrop.class);
        return EFFECTS;
    }

    @Nonnull
    private static String formatName(@Nonnull String name) {
        return IRegistrable.toRegistryName(name.replaceAll(" ", ""));
    }

    private static void register(@Nonnull Class<? extends IEffect> effect) {
        String name = formatName(effect.getSimpleName());
        try {
            EFFECTS.put(IRegistrable.toRegistryName(name), effect.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Error creating instance of effect " + name, e);
        }
    }

    @Nullable
    public static IEffect getEffect(@Nonnull String name) {
        name = formatName(name);
        return EFFECTS.get(name);
    }

}
