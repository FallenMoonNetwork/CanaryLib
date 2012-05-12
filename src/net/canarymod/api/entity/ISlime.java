package net.canarymod.api.entity;

public interface ISlime extends IEntityMob {

	public enum Size {
		TINY,
		SMALL,
		BIG;
		
		// TODO: Add these odd methods here
	};

    /**
     * Gets the size of the slime
     * @return size
     */
	public Size getSize();

    /**
     * Sets the size of the slime
     * @param size
     */	
	public void setSize(Size size);
}
