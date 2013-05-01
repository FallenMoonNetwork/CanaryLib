package net.canarymod.api.world.blocks;


/**
 * Wraps a sign block
 * 
 * @author Chris Ksoll
 * 
 */
public interface Sign extends ComplexBlock {

    /**
     * Get this signs text
     * 
     * @return
     */
    public String[] getText();

    /**
     * Get text in specified line
     * 
     * @param line
     * @return
     */
    public String getTextOnLine(int line);

    /**
     * Override the whole sign content
     * 
     * @param text
     */
    public void setText(String[] text);

    /**
     * Set text on this line
     * 
     * @param text
     * @param line
     */
    public void setTextOnLine(String text, int line);

    /**
     * Gets whether the Sign is hanging on a wall or not
     * 
     * @return {@code true} if Wall Sign; {@code false} if not
     */
    public boolean isWallSign();

    /**
     * Gets whether the Sign is sitting on a {@link Block} or not
     * 
     * @return {@code true} if Sign Post; {@code false} if not
     */
    public boolean isSignPost();
}
