package drsmugleaf.noscraft.client.renderer.entity;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.renderer.OBJRender;
import drsmugleaf.noscraft.common.entity.EntityNuke;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class RenderNuke extends OBJRender<EntityNuke> {

    public RenderNuke(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityModel() {
        return new ResourceLocation(Noscraft.MOD_ID, "block/nuke.obj");
    }

    @Override
    protected boolean preRender(EntityNuke entity, BufferBuilder buffer, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.translate(0, -entity.height / 2F, 0);
        GlStateManager.rotate(90, 1, 0, 0);

        return entity.ticksExisted > 2;
    }

}
