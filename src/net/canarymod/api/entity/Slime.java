package net.canarymod.api.entity;

public interface Slime extends EntityMob {

    public enum Size {
        TINY, SMALL, BIG;

        // TODO: Add these odd methods here
    };

    /**
     * Gets the size of the slime
     * 
     * @return size
     */
    public Size getSize();

    /**
     * Sets the size of the slime
     * 
     * @param size
     */
    public void setSize(Size size);
}
