package net.canarymod.permissionsystem;

import java.util.HashMap;

/**
 * A permission node. This represents a permission. Who would have thought
 * @author Chris
 *
 */
public class PermissionNode {
    
    private boolean value = false;
    
    private String name;
    
    private HashMap<String, PermissionNode> childs = new HashMap<String, PermissionNode>();
    
    private PermissionNode parent = null;
    
    /**
     * Create a new PermissionNode
     * @param name
     * @param value
     */
    public PermissionNode(String name, boolean value) {
        if(name == null) {
            throw new IllegalArgumentException("PermissionNode: Name cannot be null!");
        }
        this.name = name;
        this.value = value;
    }
    
    /**
     * Create a new PermissionNode
     * 
     * @param name
     * @param value
     * @param parent
     */
    protected PermissionNode(String name, boolean value, PermissionNode parent) {
        if(name == null) {
            throw new IllegalArgumentException("PermissionNode: Name cannot be null!");
        }
        this.name = name;
        this.value = value;
        this.parent = parent;
    }
    
    /**
     * Sets the parent node.
     * 
     * @param parent
     */
    protected void setParentNode(PermissionNode parent) {
    	this.parent = parent;
    }
    
    /**
     * Gets the parent node
     * 
     * @return parent node or null of none
     */
    protected PermissionNode getParentNode() {
    	return parent;
    }
    
    /**
     * Get the value of this node
     * @return
     */
    public boolean getValue() {
        return value;
    }
    
    /**
     * Override the initially given value for this node
     * @param value
     */
    public void setValue(boolean value) {
        this.value = value;
    }
    
    /**
     * Get the name of this node
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get a child node of this node with the given name
     * @param child
     * @return
     */
    public PermissionNode getChildNode(String child) {
        return childs.get(child);
    }
    
    /**
     * Check if this child node exists already
     * @param child
     * @return
     */
    public boolean hasChildNode(String child) {
        return childs.containsKey(child);
    }
    
    /**
     * add a new child node with name and value
     * @param name
     * @param value
     */
    public void addChildNode(String name, boolean value) {
        childs.put(name, new PermissionNode(name, value, this));
    }
    
    /**
     * Put the given PermissionNode into the child list of this PermissionNode
     * @param child
     */
    public void addChildNode(PermissionNode child) {
    	child.setParentNode(this);
        childs.put(child.getName(), child);
    }
    
    /**
     * Check if this is an asterisk permission, granting access to all subsequent nodes
     * @return
     */
    public boolean isAsterisk() {
        return name.equalsIgnoreCase("*");
    }
}
