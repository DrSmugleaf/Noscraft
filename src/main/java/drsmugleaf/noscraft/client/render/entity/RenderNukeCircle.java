package drsmugleaf.noscraft.client.render.entity;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.render.OBJRender;
import drsmugleaf.noscraft.common.entity.EntityNukeCircle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 18/02/2019
 */
public class RenderNukeCircle extends OBJRender<EntityNukeCircle> {

    private static final int modifier = 5;
    private int alpha = 0x00;
    private boolean positive = true;

    public RenderNukeCircle(@Nonnull RenderManager renderManager) {
        super(renderManager);
    }

    @Nonnull
    @Override
    protected ResourceLocation getEntityModel() {
        return new ResourceLocation(Noscraft.MOD_ID, "block/nuke_circle.obj");
    }

    @Override
    protected int getQuadColor(@Nonnull EntityNukeCircle entity) {
        if (positive) {
            alpha += modifier;
            if (alpha >= 0xDD) {
                positive = false;
            }
        } else {
            alpha -= modifier;
            if (alpha <= 0x22) {
                positive = true;
            }
        }

        return 0x00FFFFFF | (alpha << 24);
    }

    @Override
    protected boolean preRender(@Nonnull EntityNukeCircle entity, @Nonnull BufferBuilder buffer, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.rotate(
                (entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks),
                1.0F,
                0.0F,
                0.0F
        );
        return entity.ticksExisted > 2;
    }

}
