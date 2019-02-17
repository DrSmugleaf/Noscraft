package drsmugleaf.noscraft.common.block;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.item.IModellable;
import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public abstract class BlockFallingMod extends BlockFalling implements IModellable {

    private final @Nonnull String NAME;

    public BlockFallingMod(@Nonnull String name) {
        super();
        NAME = name;
        name = IModellable.toRegistryName(name);
        setRegistryName(new ResourceLocation(Noscraft.MOD_ID, name));
        setUnlocalizedName(getRegistryName().toString());
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
