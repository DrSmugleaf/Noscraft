package drsmugleaf.noscraft.common.item.equipment;

import drsmugleaf.noscraft.common.container.noscraft.SlotEquipment;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 04/03/2019
 */
public enum Slots {

    MAIN_WEAPON("Main Weapon", -20, -20),
    CLOTHES("Clothes", 0, 0),
    HATS("Hats", 0, -40),
    GLOVES("Gloves", -20, 0),
    SHOES("Shoes", 20, 0),
    SECONDARY_WEAPON("Secondary Weapon", 20, -20),
    NECKLACE("Necklace", 0, 20),
    RING("Ring", -20, 20),
    ARMLET("Armlet", 20, 20),
    ACCESSORY("Accessory", -20, -40),
    FAIRY("Fairy", 20, -40),
    SCROLL("Scroll", -40, -40),
    SPECIALIST_CARD("Specialist Card", 0, -20),
    NOSMALL_DRESS("NosMall Dress", -40, 0),
    NOSMALL_HAT("NosMall Hat", -40, -20),
    NOSMALL_WEAPON("NosMall Weapon", -40, 20);

    private final @Nonnull String NAME;
    private final int X;
    private final int Y;

    Slots(@Nonnull String name, int x, int y) {
        NAME = name;
        X = x;
        Y = y;
    }

    @Nonnull
    public static Slots from(@Nonnull String name) {
        for (Slots slot : values()) {
            if (slot.NAME.equalsIgnoreCase(name)) {
                return slot;
            }
        }

        throw new IllegalArgumentException("No slot found with name " + name);
    }

    @Nonnull
    public String getName() {
        return NAME;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Nonnull
    public SlotEquipment getSlot(IItemHandler handler, int x, int y) {
        String slotName = NAME.toLowerCase().replaceAll(" ", "_");
        return new SlotEquipment(slotName, handler, ordinal(), x, y);
    }

}
