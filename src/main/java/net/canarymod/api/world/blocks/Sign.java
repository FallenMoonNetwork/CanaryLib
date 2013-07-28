package net.canarymod.api.world.blocks;

/**
 * Wraps a sign block
 * 
 * @author Chris (damagefilter)
 */
public interface Sign extends ComplexBlock {

    /**
     * Get this signs text
     * 
     * @return the String array of text
     */
    public String[] getText();

    /**
     * Get text in specified line
     * 
     * @param line
     *            the line index (0 - 3)
     * @return the line of text
     */
    public String getTextOnLine(int line);

    /**
     * Override the whole sign content
     * 
     * @param text
     *            the text to set
     */
    public void setText(String[] text);

    /**
     * Set text on this line
     * 
     * @param text
     *            the text to set on the line
     * @param line
     *            the line index
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

    /**
     * Gets the {@link Block} that the Sign is attached to
     * 
     * @return the attached to {@link Block}
     */
    public Block getBlockAttached();
}
