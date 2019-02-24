package drsmugleaf.noscraft.client.keybinding;

import net.minecraft.client.settings.KeyBinding;

/**
 * Created by DrSmugleaf on 19/02/2019
 */
public class KeyBindingSkill extends KeyBinding {

    private final int ID;

    public KeyBindingSkill(int id, String description, int keyCode, String category) {
        super(description, keyCode, category);
        ID = id;
    }

    public int getId() {
        return ID;
    }

}
