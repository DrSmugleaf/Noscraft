package drsmugleaf.noscraft.common.block;

import drsmugleaf.noscraft.common.IModellable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public abstract class BlockMod extends Block implements IModellable {

    private final @Nonnull String NAME;

    public BlockMod(@Nonnull String name) {
        super(Material.ROCK);
        NAME = name;
        register(this);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Nonnull
    @Override
    public String getLayer0Path() {
        return LAYER0_PREFIX + "block/" + toRegistryName();
    }

    @Nonnull
    @Override
    public String getName() {
        return NAME;
    }

}
