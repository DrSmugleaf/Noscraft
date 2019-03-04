package drsmugleaf.noscraft.common;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 09/02/2019
 */
public interface IModellable extends IRegistrable {

    @Nonnull String LAYER0_PREFIX = Noscraft.MOD_ID + ":items/";

    @Nonnull String getLayer0Path();

    @Nonnull
    default ModelResourceLocation getModelResourceLocation() {
        return new ModelResourceLocation(Noscraft.MOD_ID + ":" + getNameToRegister(), "inventory");
    }

}
