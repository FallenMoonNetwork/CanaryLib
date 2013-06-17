package net.canarymod.api.entity;

public interface Projectile{

    /**
     * Sets the Motion of a Projectile and Rotation
     * 
     * @param motionX
     * @param motionY
     * @param motionZ
     * @param rotationYaw
     * @param rotationPitch
     */
    public void setProjectileHeading(double motionX, double motionY, double motionZ, float rotationYaw, float rotationPitch);

}
