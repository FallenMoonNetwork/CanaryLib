package net.canarymod.api.world.blocks;

/**
 * Wraps a sign block
 * 
 * @author Chris
 * 
 */
public interface SignBlock extends Block, ComplexBlock {

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
}
