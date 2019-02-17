package drsmugleaf.noscraft.client.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.obj.OBJModel.OBJBakedModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class ItemBakedModel extends OBJBakedModel {

    public ItemStack stack;
    public EntityLivingBase entity;

    public ItemBakedModel(OBJModel model, IModelState state, VertexFormat format, ImmutableMap<String, TextureAtlasSprite> textures) {
        model.super(model, state, format, textures);
    }

    @Nonnull
    @Override
    public ItemOverrideList getOverrides() {
        return ItemBakedModelOverrideList.INSTANCE;
    }

    @Nonnull
    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType type) {
        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        Pair<? extends IBakedModel, Matrix4f> ret = super.handlePerspective(type);
        if (
                entity != null &&
                entity.isInvisible() &&
                (type == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND ||
                 type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND ||
                 type == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND ||
                 type == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
            ) {
            ret.getRight().setScale(0);
        }

        return ret;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(IBlockState blockState, EnumFacing side, long rand) {
        List<BakedQuad> ret = super.getQuads(blockState, side, rand);
        for (BakedQuad quad : ret) {
            if (!quad.hasTintIndex() && stack != null) {
                ReflectionHelper.setPrivateValue(BakedQuad.class, quad, 1, 1);
            }
        }

        return ret;
    }

    private static class ItemBakedModelOverrideList extends ItemOverrideList {

        public static final ItemBakedModelOverrideList INSTANCE = new ItemBakedModelOverrideList();

        private ItemBakedModelOverrideList() {
            super(ImmutableList.of());
        }

        @Nonnull
        @Override
        public IBakedModel handleItemState(@Nonnull IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
            IBakedModel model = super.handleItemState(originalModel, stack, world, entity);
            if (model instanceof ItemBakedModel) {
                ((ItemBakedModel) model).stack = stack;
                ((ItemBakedModel) model).entity = entity;
            }

            return model;
        }

    }

}
