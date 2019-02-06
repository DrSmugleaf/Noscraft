package drsmugleaf.noscraft.common.item.equipment.armor;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ItemModArmor extends ItemArmor {

    private final @Nonnull String NAME;
    private final @Nonnull String FILE_NAME;

    public ItemModArmor(@Nonnull EntityEquipmentSlot slot, @Nonnull String name) {
        super(ArmorMaterial.LEATHER, 0, slot);
        setCreativeTab(CreativeTabs.COMBAT);
        NAME = name;
        FILE_NAME = name.replace(' ', '_').replaceAll("[':]", "").toLowerCase();
        setRegistryName(new ResourceLocation(Noscraft.MOD_ID, FILE_NAME));
        setUnlocalizedName(getRegistryName().toString());

        if (!Noscraft.getProxy().isServer()) {
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("noscraft:" + FILE_NAME, "inventory"));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return NAME;
    }

}
