package drsmugleaf.noscraft.common.container.noscraft;

import drsmugleaf.noscraft.common.item.equipment.Slots;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public class Equipment {

    @Nonnull
    private final Map<Slots, EquipmentContainer> SLOTS;

    public Equipment() {
        Map<Slots, EquipmentContainer> containers = new EnumMap<>(Slots.class);
        for (Slots slot : Slots.values()) {
            containers.put(slot, new EquipmentContainer(slot));
        }

        SLOTS = containers;
    }

    @Nonnull
    public Map<Slots, EquipmentContainer> getSlots() {
        return SLOTS;
    }

}
