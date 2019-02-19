package drsmugleaf.noscraft.client.render.entity;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.render.OBJRender;
import drsmugleaf.noscraft.common.entity.EntityNuke;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class RenderNuke extends OBJRender<EntityNuke> {

    public RenderNuke(@Nonnull RenderManager renderManager) {
        super(renderManager);
    }

    @Nonnull
    @Override
    protected ResourceLocation getEntityModel() {
        return new ResourceLocation(Noscraft.MOD_ID, "block/nuke.obj");
    }

    @Override
    protected boolean preRender(@Nonnull EntityNuke entity, @Nonnull BufferBuilder buffer, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.translate(0, -entity.height / 2F, 0);
        GlStateManager.rotate(
                (entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks),
                1.0F,
                0.0F,
                0.0F
        );
        return entity.ticksExisted > 2;
    }

}
