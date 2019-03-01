package drsmugleaf.noscraft.common.item.equipment.armor;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.IModellable;
import drsmugleaf.noscraft.common.IRegistrable;
import drsmugleaf.noscraft.common.classes.Classes;
import drsmugleaf.noscraft.common.classes.IClassSpecific;
import drsmugleaf.noscraft.util.Json;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ItemModArmor extends ItemArmor implements IModellable, IClassSpecific {

    private final @Nonnull String NAME;
    private final @Nonnull Classes CLASS;

    public ItemModArmor(@Nonnull EntityEquipmentSlot slot, @Nonnull String name, @Nonnull Classes clazz) {
        super(ArmorMaterial.LEATHER, 0, slot);
        NAME = name;
        CLASS = clazz;
        setCreativeTab(getClassCreativeTab());

        register(this);
        if (Noscraft.getProxy().isClient()) {
            ModelLoader.setCustomModelResourceLocation(this, 0, getModelResourceLocation());
        }

        Json.writeJson(this, "D:\\Projects\\Java\\Noscraft\\src\\main\\resources\\assets\\noscraft\\models\\item\\" + IRegistrable.toRegistryName(clazz.name()), IRegistrable.toRegistryName(name));
    }

//    public int getMeleeDef() {
//        return (int) Math.floor((MELEE_DEF + 0.25 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
//    }
//
//    public int getRangeDef() {
//        return (int) Math.floor((RANGE_DEF + 0.25 * getStatIncrease())* OPTIMIZATION.getExtraStats());
//    }
//
//    public int getMagicDef() {
//        return (int) Math.floor((MAGIC_DEF + 0.25 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
//    }
//
//    public int getDodge() {
//        return (int) Math.floor((DODGE + 0.25 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
//    }
//
//    public int getStatIncrease() {
//        int rarityCapacity = (LEVEL / 5) + 1; // This being an int is intentional; the original game truncates decimals.
//        return (rarityCapacity * RARITY.getStrengtheningFactor()) - RARITY.getStatModifier();
//    }

    @Override
    public @Nonnull String getLayer0Path() {
        return LAYER0_PREFIX + "armor/" + CLASS.name().toLowerCase() + "/" + IRegistrable.toRegistryName(NAME);
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return NAME;
    }

    @Nonnull
    @Override
    public Set<Classes> getClasses() {
        return CLASS.setOf();
    }

    @Nonnull
    @Override
    public String getNameToRegister() {
        return CLASS.name() + "." + NAME;
    }

}
