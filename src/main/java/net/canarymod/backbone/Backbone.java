package net.canarymod.backbone;


public class Backbone {
    
    private System system;
    
    public Backbone(System system) {
        this.system = system;
    }
    
    /**
     * The backbone system.
     * 
     * @author Chris
     * 
     */
    public enum System {
        BANS, GROUPS, WARPS, KITS, PERMISSIONS, USERS;
    }

    /**
     * Get the System of this backbone
     * 
     * @return
     */
    public System getSystem() {
        return system;
    }

}
