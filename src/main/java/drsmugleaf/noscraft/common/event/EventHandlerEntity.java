package drsmugleaf.noscraft.common.event;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.container.noscraft.Equipment;
import drsmugleaf.noscraft.common.container.noscraft.EquipmentCapabilities;
import drsmugleaf.noscraft.common.container.noscraft.EquipmentCapabilityProvider;
import drsmugleaf.noscraft.common.container.skill.SkillCapabilities;
import drsmugleaf.noscraft.common.container.skill.SkillCapabilityProvider;
import drsmugleaf.noscraft.common.container.skill.SkillsLearned;
import drsmugleaf.noscraft.common.network.PacketSync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class EventHandlerEntity {

    private static final @Nonnull HashMap<UUID, ItemStack> fairy = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            return;
        }

        try {
            Equipment container = event.getOriginal().getCapability(EquipmentCapabilities.CAPABILITY_EQUIPMENT, null);
            NBTTagCompound nbt = container.serializeNBT();
            Equipment container2 = event.getEntityPlayer().getCapability(EquipmentCapabilities.CAPABILITY_EQUIPMENT, null);
            container2.deserializeNBT(nbt);

            SkillsLearned skills = event.getOriginal().getCapability(SkillCapabilities.CAPABILITY_SKILLS, null);
            NBTTagCompound nbtSkills = skills.serializeNBT();
            SkillsLearned skills2 = event.getEntityPlayer().getCapability(SkillCapabilities.CAPABILITY_SKILLS, null);
            skills2.deserializeNBT(nbtSkills);
        } catch (Exception e) {
            Noscraft.LOG.error("Could not clone player " + event.getOriginal().getName() + " data after death");
        }
    }

    @SubscribeEvent
    public static void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(
                    new ResourceLocation(Noscraft.MOD_ID, "equipment"),
                    new EquipmentCapabilityProvider(new Equipment())
            );
            event.addCapability(
                    new ResourceLocation(Noscraft.MOD_ID, "skills"),
                    new SkillCapabilityProvider(new SkillsLearned())
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) entity;
            Set<EntityPlayer> receivers = new HashSet<>();
            receivers.add(player);
            syncSlot(player, 0, receivers);
        }
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();
        if (target instanceof EntityPlayerMP) {
            Set<EntityPlayer> receivers = new HashSet<>();
            receivers.add(event.getEntityPlayer());
            syncSlot((EntityPlayer) target, 0, receivers);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
        fairy.remove(event.player.getUniqueID());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;

            if (!player.world.isRemote) {
                Equipment equipment = player.getCapability(EquipmentCapabilities.CAPABILITY_EQUIPMENT, null);
//                ItemStack stack = equipment.getStackInSlot(0);
//                IFairy item = stack.getCapability(EquipmentCapabilities.CAPABILITY_EQUIPMENT, null);
//
//                if (equipment.isChanged() || item != null) {
//                    UUID playerID = player.getUniqueID();
//                    Set<EntityPlayer> receivers = new HashSet<>(((WorldServer) player.world).getEntityTracker().getTrackingPlayers(player));
//                    syncSlot(player, 0, stack, receivers);
//
//                    equipment.setChanged(false);
//
//                    if (fairy.get(playerID) == null) {
//                        fairy.put(playerID, ItemStack.EMPTY);
//                    } else {
//                        fairy.put(playerID, stack.copy());
//                    }
//                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(PlayerDropsEvent event) {
        World world = event.getEntity().world;
        EntityPlayer player = event.getEntityPlayer();

        if (player instanceof EntityPlayerMP && !world.isRemote && !world.getGameRules().getBoolean("keepInventory")) {
            Equipment equipment = player.getCapability(EquipmentCapabilities.CAPABILITY_EQUIPMENT, null);

//            if (equipment.getStackInSlot(0).isEmpty()) {
//                EntityItem item = new EntityItem(player.world, player.posX, player.posY + player.getEyeHeight(), player.posZ, equipment.getStackInSlot(0).copy());
//                item.setPickupDelay(40);
//                float random1 = player.world.rand.nextFloat() * 0.5F;
//                float random2 = player.world.rand.nextFloat() * (float) Math.PI * 2.0F;
//                item.motionX = (double) (-MathHelper.sin(random2) * random1);
//                item.motionZ = (double) (MathHelper.cos(random2) * random1);
//                item.motionY = 0.2D;
//                event.getDrops().add(item);
//                equipment.setStackInSlot(0, ItemStack.EMPTY);
//            }
        }
    }

    private static void syncSlot(EntityPlayer player, int slot, Set<? extends EntityPlayer> receivers) {
        Equipment container = player.getCapability(EquipmentCapabilities.CAPABILITY_EQUIPMENT, null);
//        ItemStack stack = container.getStackInSlot(slot);
//        syncSlot(player, slot, stack, receivers);
    }

    private static void syncSlot(EntityPlayer player, int slot, ItemStack stack, Set<? extends EntityPlayer> receivers) {
        PacketSync packet = new PacketSync(player, slot, stack);
        for (EntityPlayer receiver : receivers) {
            packet.sendTo((EntityPlayerMP) receiver);
        }
    }

}
