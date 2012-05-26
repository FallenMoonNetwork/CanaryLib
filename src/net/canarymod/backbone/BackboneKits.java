package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.kit.Kit;

public class BackboneKits extends Backbone {
    
    Database db;
    
    public BackboneKits(Database database, Database.Type type) {
        super(Backbone.System.KITS, type);
        db = database;
    }
    
    private boolean kitExists(Kit kit) {
        DatabaseTable table = db.getTable("kits");
        DatabaseRow[] rows = table.getFilteredRows("name", kit.getName());
        if(rows == null || rows.length == 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Add a new Kit to the list of Kits.
     * 
     * @param Kit
     */
    public void addKit(Kit kit) {
        if(kitExists(kit)) {
            updateKit(kit);
            return;
        }
        DatabaseTable table = db.getTable("kits");
        DatabaseRow newKit = table.addRow();
        newKit.setStringCell("name", kit.getName());
        newKit.setStringCell("owner", Canary.glueString(kit.getOwner(), 0, ","));
        newKit.setStringCell("groups", Canary.glueString(kit.getGroups(), 0, ","));
        StringBuilder items = new StringBuilder();
//        for(Item i : kit.getContent()) {
////            items.append(i.serialize()).append("::"); XXX
//        }
        newKit.setStringCell("contents", items.toString());
    }

    /**
     * Remove a Kit from the data source
     * 
     * @param Kit
     */
    public void removeKit(Kit kit) {
        DatabaseTable table = db.getTable("kits");
        DatabaseRow[] toRemove = table.getFilteredRows("name", kit.getName());
        if(toRemove != null && toRemove.length == 1) {
            DatabaseRow row = toRemove[0];
            table.removeRow(row);
            
        }
    }

    /**
     * Get a Kit for this player name
     * 
     * @param name
     * @return Returns a Kit object if that Kit was found, null otherwise
     */
    public Kit getKit(String name) {
        DatabaseTable table = db.getTable("kits");
        DatabaseRow[] toRemove = table.getFilteredRows("name", name);
        if(toRemove != null && toRemove.length == 1) {
//            DatabaseRow row = toRemove[0];
//            Kit kit = new Kit();
//            ArrayList<Item> items = new ArrayList<Item>();
//            String[] itemSplit = row.getStringCell("contents").split("::");
//            for(String is : itemSplit) {
//                items.add(new Item().deserialize(is)); XXX
//            }
//            kit.set
        }
        return null;
    }

    /**
     * Update a Kit
     * 
     * @param Kit
     */
    public void updateKit(Kit kit) {
    }

    /**
     * Load and return all recorded bans
     * 
     * @return
     */
    public ArrayList<Kit> loadKits() {
        return null;
    }
}
