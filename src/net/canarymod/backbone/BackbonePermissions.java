package net.canarymod.backbone;

import java.util.HashMap;

import net.canarymod.database.Database;
import net.canarymod.database.Database.Type;
//import net.canarymod.database.DatabaseRow;
import net.canarymod.group.Group;
import net.canarymod.permissionsystem.PermissionNode;

/**
 * Backbone to the permissions System. This contains NO logic, it is only the
 * data source access!
 * 
 * @author Jos
 * 
 */
public class BackbonePermissions extends Backbone {
    Database db;
    
    public BackbonePermissions(Database database, Database.Type type) {
        super(Backbone.System.PERMISSIONS, type);
        db = database;
    }
    public BackbonePermissions(System system, Type type) {
        super(system, type);
        // TODO Auto-generated constructor stub
    }
    
    public HashMap<String,PermissionNode> loadGroupPermissions(Group group) {
//        DatabaseRow[] rel = db.getTable("gprelations").getFilteredRows("groupId", Integer.toString(db.getTable("groups").getFilteredRows("name",group.name)[0].getIntCell("id")));
        
        return null;
    }

}
