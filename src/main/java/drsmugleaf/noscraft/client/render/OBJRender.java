package drsmugleaf.noscraft.client.render;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.ClientProxy;
import drsmugleaf.noscraft.client.model.ItemBakedModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.pipeline.LightUtil;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public abstract class OBJRender<T extends Entity> extends Render<T> {

    @Nullable
    private IBakedModel model;

    public OBJRender(@Nonnull RenderManager renderManager) {
        super(renderManager);
    }

    @Nonnull
    protected abstract ResourceLocation getEntityModel();

    protected float[] getColor(@Nonnull T entity) {
        return new float[]{1.0F, 1.0F, 1.0F, 1.0F};
    }

    protected int getQuadColor(@Nonnull T entity) {
        return -1;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T entity) {
        return null;
    }

    protected abstract boolean preRender(@Nonnull T entity, @Nonnull BufferBuilder buffer, double x, double y, double z, float entityYaw, float partialTicks);

    @Override
    public void doRender(@Nonnull T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (model == null) {
            IModel model = ModelLoaderRegistry.getModelOrLogError(getEntityModel(), Noscraft.MOD_NAME + " is missing a model. Please report this to the mod authors.");
            IBakedModel bakedModel = model.bake(model.getDefaultState(), DefaultVertexFormats.ITEM, ModelLoader.defaultTextureGetter());
            if (bakedModel instanceof OBJModel.OBJBakedModel && model instanceof OBJModel){
                this.model = new ItemBakedModel((OBJModel) model, ((OBJModel.OBJBakedModel) bakedModel).getState(), DefaultVertexFormats.ITEM, ClientProxy.getTextures((OBJModel) model));
            }
        }

        if (model == null) {
            return;
        }

        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        float[] color = getColor(entity);
        GlStateManager.color(color[0], color[1], color[2], color[3]);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );
        GlStateManager.pushMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);

        GlStateManager.rotate(180, 0, 0, 1);
        GlStateManager.translate((float) -x, (float) -y, (float) z);
        GlStateManager.rotate(
                entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks,
                0.0F,
                1.0F,
                0.0F
        );

        if (preRender(entity, buffer, x, y, z, entityYaw, partialTicks)) {
            GlStateManager.rotate(
                    -(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks),
                    1.0F,
                    0.0F,
                    0.0F
            );
            int quadColor = getQuadColor(entity);

            for (EnumFacing side : EnumFacing.values()) {
                List<BakedQuad> quads = model.getQuads(null, side, 0);
                if (!quads.isEmpty()) {
                    for (BakedQuad quad : quads) {
                        LightUtil.renderQuadColor(buffer, quad, quadColor);
                    }
                }
            }

            List<BakedQuad> quads = model.getQuads(null, null, 0);
            if (!quads.isEmpty()) {
                for (BakedQuad quad : quads) {
                    LightUtil.renderQuadColor(buffer, quad, quadColor);
                }
            }
        }

        buffer.setTranslation(0, 0, 0);
        tessellator.draw();

        GlStateManager.cullFace(GlStateManager.CullFace.BACK);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
    }

}
