package net.canarymod.backbone;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.inventory.Item;
import net.canarymod.database.DatabaseRow;
import net.canarymod.database.DatabaseTable;
import net.canarymod.kit.Kit;

public class BackboneKits extends Backbone {
    
    public BackboneKits() {
        super(Backbone.System.KITS);
    }
    
    private boolean kitExists(Kit kit) {
        DatabaseRow[] rows = getTable().getFilteredRows("name", kit.getName());
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
        DatabaseRow newKit = getTable().addRow();
        newKit.setStringCell("name", kit.getName());
        newKit.setStringCell("owner", Canary.glueString(kit.getOwner(), 0, ","));
        newKit.setStringCell("groups", Canary.glueString(kit.getGroups(), 0, ","));
        StringBuilder items = new StringBuilder();
        for(Item i : kit.getContent()) {
            items.append(Canary.serialize(i)).append("::");
        }
        newKit.setStringCell("contents", items.toString());
        newKit.setIntCell("useDelay", kit.getDelay());
    }

    /**
     * Remove a Kit from the data source
     * 
     * @param Kit
     */
    public void removeKit(Kit kit) {
        DatabaseTable table = getTable();
        DatabaseRow[] toRemove = table.getFilteredRows("name", kit.getName());
        if(toRemove != null && toRemove.length == 1) {
            DatabaseRow row = toRemove[0];
            table.removeRow(row);
            
        }
    }

    /**
     * Get a Kit with the given name
     * 
     * @param name
     * @return Returns a Kit object if that Kit was found, null otherwise
     */
    public Kit getKit(String name) {
        DatabaseRow[] toRemove = getTable().getFilteredRows("name", name);
        if(toRemove != null && toRemove.length == 1) {
            DatabaseRow row = toRemove[0];
            Kit kit = new Kit();
            ArrayList<Item> items = new ArrayList<Item>();
            String[] itemSplit = row.getStringCell("contents").split("::");
            for(String is : itemSplit) {
                items.add((Item) Canary.deserialize(is, "Item"));
            }
            kit.setContent(items);
            kit.setDelay(row.getIntCell("useDelay"));
            kit.setGroups(row.getStringCell("groups").split(","));
            kit.setOwner(row.getStringCell("owner").split(","));
            kit.setName(row.getStringCell("name"));
            return kit;
        }
        return null;
    }
    
    private Kit getKit(DatabaseRow toLoad) {
            Kit kit = new Kit();
            ArrayList<Item> items = new ArrayList<Item>();
            String[] itemSplit = toLoad.getStringCell("contents").split("::");
            for(String is : itemSplit) {
                items.add((Item) Canary.deserialize(is, "Item"));
            }
            kit.setContent(items);
            kit.setDelay(toLoad.getIntCell("useDelay"));
            kit.setGroups(toLoad.getStringCell("groups").split(","));
            kit.setOwner(toLoad.getStringCell("owner").split(","));
            kit.setName(toLoad.getStringCell("name"));
            return kit;
    }

    /**
     * Update a Kit
     * 
     * @param Kit
     */
    public void updateKit(Kit kit) {
        DatabaseRow[] toUpdate = getTable().getFilteredRows("name", kit.getName());
        if(toUpdate != null && toUpdate.length == 1) {
            DatabaseRow row = toUpdate[0];
            row.setStringCell("name", kit.getName());
            row.setStringCell("groups", Canary.glueString(kit.getGroups(), 0, ","));
            row.setStringCell("owner", Canary.glueString(kit.getOwner(), 0, ","));
            StringBuilder items = new StringBuilder();
            for(Item i : kit.getContent()) {
                items.append(Canary.serialize(i)).append("::");
            }
            row.setStringCell("contents", items.toString());
            row.setIntCell("useDelay", kit.getDelay());
        }
    }

    /**
     * Load and return all kits
     * 
     * @return
     */
    public ArrayList<Kit> loadKits() {
        DatabaseTable table = getTable();
        DatabaseRow[] toLoad = table.getAllRows();
        ArrayList<Kit> kits = new ArrayList<Kit>();
        if(toLoad != null && toLoad.length > 0) {
            for(DatabaseRow row : toLoad) {
                Kit k = getKit(row);
                if(k != null) {
                    kits.add(k);
                }
            }
        }
        return kits;
    }

    private DatabaseTable getTable() {
        DatabaseTable table = Canary.db().getTable("kits");
        if(table == null) {
            Canary.db().prepare();
            table = Canary.db().addTable("kits");
            table.appendColumn("name", DatabaseTable.ColumnType.STRING);
            table.appendColumn("groups", DatabaseTable.ColumnType.STRING);
            table.appendColumn("owner", DatabaseTable.ColumnType.STRING);
            table.appendColumn("contents", DatabaseTable.ColumnType.STRING);
            table.appendColumn("useDelay", DatabaseTable.ColumnType.INTEGER);
            Canary.db().execute();
        }
        
        return table;
    }
}
