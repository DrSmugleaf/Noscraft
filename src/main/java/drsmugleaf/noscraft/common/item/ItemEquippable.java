package drsmugleaf.noscraft.common.item;

import com.google.common.collect.ImmutableSet;
import drsmugleaf.noscraft.common.classes.Classes;
import drsmugleaf.noscraft.common.classes.IClassSpecific;
import drsmugleaf.noscraft.common.item.equipment.Slots;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by DrSmugleaf on 04/03/2019
 */
public class ItemEquippable extends ItemMod implements IClassSpecific {

    private final int ID;
    private final @Nonnull String CATEGORY;
    private final @Nonnull Slots SLOT;
    private final @Nullable Integer VISUAL_CHANGE;
    private final @Nullable Integer GOLD_PRICE;
    private final @Nullable Integer REPUTATION_PRICE;
    private final @Nonnull ImmutableSet<Classes> CLASSES;
    private final int ITEM_LVL;

    public ItemEquippable(
            int id,
            @Nonnull String name,
            @Nonnull String registryName,
            @Nonnull String category,
            @Nonnull Slots slot,
            @Nullable Integer visualChange,
            @Nullable Integer goldPrice,
            @Nullable Integer reputationPrice,
            @Nonnull ImmutableSet<Classes> classes,
            int itemLvl
    ) {
        super(name, registryName);
        ID = id;
        CATEGORY = category;
        SLOT = slot;
        VISUAL_CHANGE = visualChange;
        GOLD_PRICE = goldPrice;
        REPUTATION_PRICE = reputationPrice;
        CLASSES = classes;
        ITEM_LVL = itemLvl;
    }

    @Nonnull
    public static ItemEquippable from(@Nonnull Map<String, String> csvLine) {
        String slot = csvLine.get("slot");
        String visualChange = csvLine.get("visualChange");
        String goldPrice = csvLine.get("goldPrice");
        String reputationPrice = csvLine.get("reputationPrice");
        String classes = csvLine.get("classRequired");
        String itemLvl = csvLine.get("itemLvl");

        return new ItemEquippable(
                Integer.parseInt(csvLine.get("id")),
                csvLine.get("name"),
                csvLine.get("registryName"),
                csvLine.get("category"),
                Slots.from(slot),
                visualChange == null ? null : Integer.parseInt(visualChange),
                goldPrice == null ? null : Integer.parseInt(goldPrice),
                reputationPrice == null ? null : Integer.parseInt(reputationPrice),
                classes == null ? Classes.all() : Classes.setOf(classes.split(", ")),
                itemLvl == null ? 0 : Integer.parseInt(itemLvl)
        );
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getItemStackLimit() {
        return 1;
    }

    @Nonnull
    @Override
    public ImmutableSet<Classes> getClasses() {
        return CLASSES;
    }

    @Nonnull
    public Slots getSlot() {
        return SLOT;
    }

}
