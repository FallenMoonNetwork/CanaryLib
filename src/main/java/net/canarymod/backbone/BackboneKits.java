package net.canarymod.backbone;

import java.util.ArrayList;
import java.util.Arrays;

import net.canarymod.Canary;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.canarymod.kit.Kit;

public class BackboneKits extends Backbone {

    public BackboneKits() {
        super(Backbone.System.KITS);
    }

    private boolean kitExists(Kit kit) {
        KitAccess data = new KitAccess();
        try {
            Database.get().load(data, new String[]{"name"}, new Object[]{kit.getName()});
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Add a new Kit to the list of Kits.
     *
     * @param KitCommand
     */
    public void addKit(Kit kit) {
        if(kitExists(kit)) {
            updateKit(kit);
            return;
        }
        KitAccess data = new KitAccess();
        data.groups = new ArrayList<String>(Arrays.asList(kit.getGroups()));
        data.items = kit.getItemsAsStringList();
        data.name = kit.getName();
        data.owners = new ArrayList<String>(Arrays.asList(kit.getOwner()));
        data.useDelay = kit.getDelay();
        data.id = 0;

        try {
            Database.get().insert(data);
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Remove a Kit from the data source
     *
     * @param KitCommand
     */
    public void removeKit(Kit kit) {
        try {
            Database.get().remove("kit", new String[]{"name"}, new Object[]{kit.getName()});
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Get a Kit with the given name
     *
     * @param name
     * @return Returns a Kit object if that Kit was found, null otherwise
     */
    public Kit getKit(String name) {
        KitAccess data = new KitAccess();
        try {
            Database.get().load(data, new String[]{"name"}, new Object[]{name});
            if(!data.hasData()) {
                return null;
            }
            Kit kit = new Kit();
            kit.setContentFromStrings(data.items);
            kit.setDelay(data.useDelay);
            kit.setGroups((String[]) data.groups.toArray());
            kit.setName(data.name);
            kit.setOwner((String[]) data.groups.toArray());
            kit.setId(data.id);
            return kit;
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Update a Kit
     *
     * @param KitCommand
     */
    public void updateKit(Kit kit) {
        KitAccess data = new KitAccess();
        data.groups = new ArrayList<String>(Arrays.asList(kit.getGroups()));
        data.items = kit.getItemsAsStringList();
        data.name = kit.getName();
        data.owners = new ArrayList<String>(Arrays.asList(kit.getOwner()));
        data.useDelay = kit.getDelay();
        try {
            Database.get().update(data, new String[]{"name"}, new Object[]{kit.getName()});
        } catch (DatabaseWriteException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }

    /**
     * Load and return all kits
     *
     * @return
     */
    public ArrayList<Kit> loadKits() {
        ArrayList<DataAccess> dataList = new ArrayList<DataAccess>();
        ArrayList<Kit> kits = new ArrayList<Kit>();
        try {
            Database.get().loadAll(new KitAccess(), dataList, new String[]{}, new Object[]{});
            for(DataAccess da : dataList) {
                KitAccess data = (KitAccess) da;
                Kit kit = new Kit();
                kit.setContentFromStrings(data.items);
                kit.setDelay(data.useDelay);
                kit.setGroups((String[]) data.groups.toArray());
                kit.setName(data.name);
                kit.setOwner((String[]) data.groups.toArray());
                kit.setId(data.id);
                kits.add(kit);
            }
            return kits;
        } catch (DatabaseReadException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        return null;
    }
}
