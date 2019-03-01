package drsmugleaf.noscraft.common.item.equipment.weapon;

import drsmugleaf.noscraft.common.IRegistrable;
import drsmugleaf.noscraft.common.classes.Classes;
import drsmugleaf.noscraft.common.classes.IClassSpecific;
import drsmugleaf.noscraft.common.item.ItemMod;
import drsmugleaf.noscraft.util.Json;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Created by DrSmugleaf on 09/02/2019
 */
public class ItemModWeapon extends ItemMod implements IClassSpecific {

    private final @Nonnull Classes CLASS;
    private final @Nonnull WeaponSlot SLOT;

    public ItemModWeapon(@Nonnull String name, @Nonnull Classes clazz, @Nonnull WeaponSlot slot) {
        super(name, clazz + "." + name);
        CLASS = clazz;
        SLOT = slot;
        setCreativeTab(getClassCreativeTab());

        Json.writeJson(this, "D:\\Projects\\Java\\Noscraft\\src\\main\\resources\\assets\\noscraft\\models\\item\\" + IRegistrable.toRegistryName(clazz.name()), IRegistrable.toRegistryName(name));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

//    public int getDamage() {
//        return (int) Math.floor((DAMAGE + 0.5 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
//    }
//
//    public int getHitRate() {
//        return (int) Math.floor((HIT_RATE + 0.5 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
//    }
//
//    public int getStatIncrease() {
//        int rarityCapacity = (LEVEL / 5) + 1; //This being an int is intentional; the original game truncates decimals.
//        return (rarityCapacity * RARITY.getStrengtheningFactor()) - RARITY.getStatModifier();
//    }

    @Override
    public @Nonnull String getLayer0Path() {
        return LAYER0_PREFIX + "weapon/" + SLOT.name().toLowerCase() + "/" + CLASS.name().toLowerCase() + "/" + IRegistrable.toRegistryName(getName());
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return getName();
    }

    @Nonnull
    @Override
    public Set<Classes> getClasses() {
        return CLASS.setOf();
    }

}
