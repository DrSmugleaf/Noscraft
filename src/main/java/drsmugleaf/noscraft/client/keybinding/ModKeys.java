package drsmugleaf.noscraft.client.keybinding;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.GuiSpellBar;
import drsmugleaf.noscraft.common.entity.EntityNukeCircle;
import drsmugleaf.noscraft.common.network.PacketHandler;
import drsmugleaf.noscraft.common.network.PacketHellDrop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

/**
 * Created by DrSmugleaf on 18/02/2019
 */
public class ModKeys {

    public static final KeyBinding ABILITY_1 = new KeyBinding("key.ability1", Keyboard.KEY_1, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_2 = new KeyBinding("key.ability2", Keyboard.KEY_2, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_3 = new KeyBinding("key.ability3", Keyboard.KEY_3, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_4 = new KeyBinding("key.ability4", Keyboard.KEY_4, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_5 = new KeyBinding("key.ability5", Keyboard.KEY_5, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_6 = new KeyBinding("key.ability6", Keyboard.KEY_6, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_7 = new KeyBinding("key.ability7", Keyboard.KEY_7, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_8 = new KeyBinding("key.ability8", Keyboard.KEY_8, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_9 = new KeyBinding("key.ability9", Keyboard.KEY_9, Noscraft.MOD_NAME);
    public static final KeyBinding ABILITY_0 = new KeyBinding("key.ability0", Keyboard.KEY_0, Noscraft.MOD_NAME);

    public static void init() {
        ClientRegistry.registerKeyBinding(ABILITY_1);
        ClientRegistry.registerKeyBinding(ABILITY_2);
        ClientRegistry.registerKeyBinding(ABILITY_3);
        ClientRegistry.registerKeyBinding(ABILITY_4);
        ClientRegistry.registerKeyBinding(ABILITY_5);
        ClientRegistry.registerKeyBinding(ABILITY_6);
        ClientRegistry.registerKeyBinding(ABILITY_7);
        ClientRegistry.registerKeyBinding(ABILITY_8);
        ClientRegistry.registerKeyBinding(ABILITY_9);
        ClientRegistry.registerKeyBinding(ABILITY_0);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null) {
            return;
        }

        if (ABILITY_0.isPressed()) {
            GuiSpellBar.pressed = true;
            RayTraceResult result = player.rayTrace(50, Minecraft.getMinecraft().getTickLength());
            if (result == null) {
                return;
            }

            World world = player.world;
            BlockPos pos = result.getBlockPos();
            PacketHandler.INSTANCE.sendToServer(new PacketHellDrop(1, pos)); // ID from ModEntities
            EntityNukeCircle circle = new EntityNukeCircle(world, pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(circle);
        }
    }

}
