package drsmugleaf.noscraft.client.keybinding;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.ModGuis;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrSmugleaf on 18/02/2019
 */
public class ModKeys {

    @Nonnull
    private static final Map<Integer, KeyBindingSkill> SKILLS = new HashMap<>();

    public static void init() {
        for (int slot = 1; slot < 11; slot++) {
            String description = "key.skill" + slot;
            int key = slot + 1;
            KeyBindingSkill bind = new KeyBindingSkill(slot, description, key, Noscraft.MOD_NAME);

            SKILLS.put(slot, bind);
            ClientRegistry.registerKeyBinding(bind);
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null) {
            return;
        }

        for (KeyBindingSkill bind : SKILLS.values()) {
            if (bind.isKeyDown()) {
                ModGuis.getSkillBar().cast(bind);
            }
        }
    }

    @Nullable
    public static KeyBindingSkill getBind(int id) {
        return SKILLS.get(id);
    }

}
