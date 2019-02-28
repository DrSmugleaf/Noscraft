package drsmugleaf.noscraft.client.particle;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 18/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class ModParticles {

    private static <T extends ParticleMod> void registerParticle(int id, @Nonnull T particle) {
        Minecraft.getMinecraft().effectRenderer.registerParticle(id, particle);
    }

    public static void init() {}

    @SubscribeEvent
    public static void onTextureStitchEventPre(TextureStitchEvent.Pre event) {}

}
