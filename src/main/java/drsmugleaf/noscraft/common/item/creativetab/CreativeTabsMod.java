package drsmugleaf.noscraft.common.item.creativetab;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public abstract class CreativeTabsMod extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":logo_icon")
    @Nonnull
    public static final Item NOSCRAFT_ICON = null;

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":piece_of_wood")
    @Nonnull
    public static final Item ADVENTURER_ICON = null;

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":bow_for_beginners")
    @Nonnull
    public static final Item ARCHER_ICON = null;

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":sword_for_beginners")
    @Nonnull
    public static final Item SWORDSMAN_ICON = null;

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":magic_stick_for_beginners")
    @Nonnull
    public static final Item MAGE_ICON = null;

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":short_knight_glove")
    @Nonnull
    public static final Item MARTIAL_ARTIST_ICON = null;

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":fire_fairy")
    @Nonnull
    public static final Item FAIRY_ICON = null;

    public static final CreativeTabs NOSCRAFT = new CreativeTabsMod(Noscraft.MOD_ID) {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(NOSCRAFT_ICON);
        }
    };

    public static final CreativeTabs ADVENTURER = new CreativeTabsMod("adventurer") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ADVENTURER_ICON);
        }
    };

    public static final CreativeTabs ARCHER = new CreativeTabsMod("archer") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ARCHER_ICON);
        }
    };

    public static final CreativeTabs SWORDSMAN = new CreativeTabsMod("swordsman") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(SWORDSMAN_ICON);
        }
    };

    public static final CreativeTabs MAGE = new CreativeTabsMod("mage") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(MAGE_ICON);
        }
    };

    public static final CreativeTabs MARTIAL_ARTIST = new CreativeTabsMod("martialartist") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(MARTIAL_ARTIST_ICON);
        }
    };

    public static final CreativeTabs FAIRY = new CreativeTabsMod("fairy") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(FAIRY_ICON);
        }
    };

    private CreativeTabsMod(@Nonnull String name) {
        super(name);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

}
