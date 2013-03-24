package net.canarymod.api;


/**
 * DataWatcher interface to manipulate data within notchian code.
 * This might aswell be used as Stand-Alone datawatcher implementation
 * along with the Notch-Wrapper.
 * @author Chris
 *
 */
public interface DataWatcher {

    /**
     * Add a new object to watch and file
     * @param index
     * @param object
     */
    public void addObject(int index, Object object);
    
    /**
     * Update the value of an already existing object
     * @param index
     * @param object
     */
    public void updateObject(int index, Object object);
    
    /**
     * Get byte value of a watchable object at index
     * @param index
     * @return
     */
    public byte getByte(int index);
    
    /**
     * Get short value of a watchable object at index
     * @param index
     * @return
     */
    public short getShort(int index);
    
    /**
     * Get int value of a watchable object at index
     * @param index
     * @return
     */
    public int getInt(int index);
    
    /**
     * Get String value of a watchable object at index
     * @param index
     * @return
     */
    public String getString(int index);
    
}
