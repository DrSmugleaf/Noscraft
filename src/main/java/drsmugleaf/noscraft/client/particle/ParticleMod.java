package drsmugleaf.noscraft.client.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 18/02/2019
 */
public abstract class ParticleMod extends Particle implements IParticleFactory {

    private float baseScale = 1;

    protected ParticleMod(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
    }

    public ParticleMod(int particleID, @Nonnull World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, @Nonnull int... p_178902_15_) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }

    public static void register(@Nonnull TextureStitchEvent.Pre event) {}

    @Nonnull
    public ParticleMod setScale(float scale) {
        particleScale = scale;
        baseScale = scale;
        return this;
    }

    @Nonnull
    public ParticleMod setColor(float red, float green, float blue) {
        setRBGColorF(red, green, blue);
        return this;
    }

    @Nonnull
    public ParticleMod setMaxAge(int age, int randomTo) {
        super.setMaxAge(age + rand.nextInt(randomTo));
        return this;
    }

    @Nonnull
    public ParticleMod setGravity(float gravity) {
        particleGravity = gravity;
        return this;
    }

    @Nonnull
    public Vec3d getPosition() {
        return new Vec3d(posX, posY, posZ);
    }

    @Nonnull
    public ParticleMod setPosition(@Nonnull Vec3d position) {
        setPosition(position.x, position.y, position.z);
        return this;
    }

    @Nonnull
    public World getWorld() {
        return world;
    }

    @Override
    public int getFXLayer() {
        return 1; // Main texture atlas
    }

}
