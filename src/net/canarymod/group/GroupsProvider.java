package net.canarymod.group;

import java.util.ArrayList;

import net.canarymod.backbone.Backbone;
import net.canarymod.backbone.BackboneGroups;

public class GroupsProvider {
    private ArrayList<Group> groups;
    private BackboneGroups backbone;

    /**
     * Instanciate a groups provider
     * 
     * @param bone
     * @param type
     */
    public GroupsProvider(Backbone bone, Backbone.Type type) {
        backbone = (BackboneGroups) bone.getBackbone(Backbone.System.GROUPS, type);
        groups = backbone.loadGroups();
    }

    /**
     * Add a new Group
     * 
     * @param g
     */
    public void addGroup(Group g) {
        backbone.addGroup(g);
        groups.add(g);
    }

    /**
     * Remove this group
     * 
     * @param g
     */
    public void removeGroup(Group g) {
        backbone.removeGroup(g);
        groups.remove(g);
    }

    /**
     * Check if a group by the given name exists
     * 
     * @param name
     * @return
     */
    public boolean groupExists(String name) {
        for (Group g : groups) {
            if (g.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the given group is filed in this groups provider
     * 
     * @param g
     * @return
     */
    public boolean groupExists(Group g) {
        return groups.contains(g);
    }

    /**
     * Return array of all existent groups
     * 
     * @return
     */
    public Group[] getGroups() {
        return (Group[]) groups.toArray();
    }

    /**
     * Returns group files under the given name or null if group not exists
     * 
     * @param name
     * @return
     */
    public Group getGroup(String name) {
        for (Group g : groups) {
            if (g.name.equals(name)) {
                return g;
            }
        }
        return null;
    }
}
