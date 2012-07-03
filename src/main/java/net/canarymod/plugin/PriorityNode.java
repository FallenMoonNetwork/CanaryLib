package net.canarymod.plugin;

/**
 * The Plugin Priority Node for "Execution order"
 * 
 * @author YLivay
 * 
 */
public class PriorityNode {
    /**
     * The priority's name.
     */
    private String name;
    /**
     * The priority's value.
     */
    private int value;
    
    /**
     * Creates a new priority with the given name and value.
     * @param name
     * @param value
     */
    public PriorityNode(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * Gets this priority's name.
     * @return This priority's name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets this priority's value.
     * @return This priority's value.
     */
    public int getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode() * this.value;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PriorityNode) {
            return (this.name.equals(((PriorityNode)obj).name) && this.value == ((PriorityNode)obj).value);
        }
        return false;
    }
    
}
