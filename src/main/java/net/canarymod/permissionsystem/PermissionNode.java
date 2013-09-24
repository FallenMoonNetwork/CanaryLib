package net.canarymod.permissionsystem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A permission node. This represents a permission. Who would have thought
 *
 * @author Chris (damagefilter)
 */
public class PermissionNode {

    private boolean value = false;

    private String name;

    /** ID in the database */
    private int id;

    private HashMap<String, PermissionNode> childs = new HashMap<String, PermissionNode>();

    private PermissionNode parent = null;

    /**
     * Create a new PermissionNode.
     *
     * @param name
     * @param value
     */
    public PermissionNode(String name, boolean value) {
        if (name == null) {
            throw new IllegalArgumentException("PermissionNode: Name cannot be null!");
        }
        this.name = name;
        this.value = value;
        this.id = -1;
    }

    /**
     * Create a new PermissionNode wit a parent.
     * This will have a volatile id until it's saved to database and loaded again.
     *
     * @param name
     * @param value
     * @param parent
     */
    protected PermissionNode(String name, boolean value, PermissionNode parent) {
        if (name == null) {
            throw new IllegalArgumentException("PermissionNode: Name cannot be null!");
        }
        this.name = name;
        this.value = value;
        this.id = 0;
        setParentNode(parent);
    }

    /**
     * Get the database ID for this node
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the database ID for this Node.
     * <b style="color:red">Do not use this unless you're dead sure what you're doing! it is HIGHLY unlikely that you will need this</b>
     *
     * @param id
     *         the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the parent node.
     *
     * @param parent
     */
    public void setParentNode(PermissionNode parent) {
        if (this.parent != null) {
            parent.childs.remove(name);
        }
        this.parent = parent;
        parent.childs.put(name, this);
    }

    /**
     * Gets the parent node
     *
     * @return parent node or null of none
     */
    public PermissionNode getParentNode() {
        return parent;
    }

    /**
     * Check if this node has a parent
     *
     * @return
     */
    public boolean hasParent() {
        return parent != null;
    }

    /**
     * Get the value of this node
     *
     * @return
     */
    public boolean getValue() {
        return value;
    }

    /**
     * Override the initially given value for this node
     *
     * @param value
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    /**
     * Get the name of this node
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the full path name for this node starting here,
     * upwards to the first node in the inheritance tree
     *
     * @return
     */
    public String getFullPath() {
        ArrayList<PermissionNode> parents = parentsToList();
        StringBuilder path = new StringBuilder();

        for (int i = parents.size() - 1; i >= 0; i--) {
            path.append(parents.get(i).name).append(".");
        }
        path.append(this.name);
        return path.toString();
    }

    /**
     * This creates a list of parents starting with this nodes parent, walking the tree upwards to the first,
     * resulting in a reverse parent list. For example if this node was canary.world.canEnter,
     * the list would be ordered like this: canEnter,world,canary
     *
     * @return
     */
    private ArrayList<PermissionNode> parentsToList() {
        ArrayList<PermissionNode> parents = new ArrayList<PermissionNode>();

        walkParents(parents, this);
        return parents;
    }

    private void walkParents(ArrayList<PermissionNode> list, PermissionNode node) {
        if (node.parent == null) {
            return; // Found topmost permission
        }
        list.add(node.parent);
        walkParents(list, node.parent);
    }

    /**
     * Get a child node of this node with the given name
     *
     * @param child
     *
     * @return
     */
    public PermissionNode getChildNode(String child) {
        return childs.get(child);
    }

    /**
     * Check if this child node exists already
     *
     * @param child
     *
     * @return
     */
    public boolean hasChildNode(String child) {
        return childs.containsKey(child);
    }

    /**
     * Get all childs for this node
     *
     * @return
     */
    public HashMap<String, PermissionNode> getChilds() {
        return childs;
    }

    /**
     * Check if this node has childs
     *
     * @return
     */
    public boolean hasChilds() {
        return childs.isEmpty();
    }

    /**
     * Put the given PermissionNode into the child list of this PermissionNode
     *
     * @param child
     */
    public void addChildNode(PermissionNode child) {
        child.setParentNode(this);
        childs.put(child.getName(), child);
    }

    /**
     * Check if this is an asterisk permission, granting access to all
     * subsequent nodes
     *
     * @return
     */
    public boolean isAsterisk() {
        return name.equals("*");
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Name: ").append(name).append(" :: Value: ").append(value).toString();
    }

    /**
     * Returns a permission node from a well formatted string.<br>
     * The String should be node.path:value<br>
     * Where value should be true or false. Value is an optional field. It will default to true
     *
     * @param in
     *
     * @return
     */
    public static PermissionNode fromString(String in) {
        String[] split = in.split(":");
        if (split.length == 1) {
            return new PermissionNode(in, true);
        }
        else {
            return new PermissionNode(split[0], Boolean.valueOf(split[1]));
        }
    }
}
