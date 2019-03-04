package drsmugleaf.noscraft.common.block;

import drsmugleaf.noscraft.common.IModellable;
import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public abstract class BlockFallingMod extends BlockFalling implements IModellable {

    private final @Nonnull String NAME;

    public BlockFallingMod(@Nonnull String name, @Nonnull String registryName) {
        super();
        NAME = name;
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        register(this);
    }

    @Nonnull
    public String getName() {
        return NAME;
    }

    @Nonnull
    @Override
    public String getLayer0Path() {
        return LAYER0_PREFIX + "block/" + toRegistryName();
    }

}
