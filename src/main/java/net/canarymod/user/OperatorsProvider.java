package net.canarymod.user;

import net.canarymod.Canary;
import net.canarymod.backbone.BackboneOperators;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Access to the backbone for operators
 *
 * @author Jason (darkdiplomat)
 */
public class OperatorsProvider {
    private BackboneOperators backboneOps;
    private ArrayList<String> ops;

    public OperatorsProvider() {
        backboneOps = new BackboneOperators();
        ops = backboneOps.loadOps();
        readOpsCfg();
    }

    /** Reload the ops from database */
    public void reload() {
        ops = backboneOps.loadOps();
        readOpsCfg();
    }

    /**
     * Reads the config/ops.cfg file if it exists and updates the database
     * with the names found in it.
     */
    private void readOpsCfg() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("config/ops.cfg")));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.startsWith("#")) {
                    continue;
                }
                if(!isOpped(line)) {
                    addPlayer(line);
                }
            }
        }
        catch (FileNotFoundException e) {
            Canary.logInfo("Could not find config/ops.cfg. Creating one for you...");
            File f = new File("config/ops.cfg");
            try {
                if(f.createNewFile()) {
                    PrintWriter pwriter = new PrintWriter(new FileWriter(f));
                    pwriter.println("# Note: This file is not guaranteed to be synchronous with the actual ops list in database.");
                    pwriter.println("# However, you may use it to quickly add new operators as you please.");
                    pwriter.println("# Any duplicate entries will be taken care of so don't worry.");
                    pwriter.println("# Lines starting with # are comments ;)");
                    pwriter.println("# Add one name to each line.");
                    pwriter.close();
                    Canary.logInfo("You can now add ops to config/ops.cfg (one per line!). We left you a note.");
                }
            }
            catch (IOException e1) {
                Canary.logSevere("Failed to write config/ops.cfg! (Probably no write-access!)", e);
            }
        }
        catch (IOException e) {
            Canary.logSevere("Failed to read from config/ops.cfg!", e);
        }
    }

    /**
     * Check if a given player is opped.
     *
     * @param player the name of a player
     *
     * @return true if player is opped, false otherwise
     */
    public boolean isOpped(String player) {
        return ops.contains(player);
    }

    /**
     * Adds a new operators entry
     *
     * @param name the player name you want to add
     */
    public void addPlayer(String name) {
        if (!ops.contains(name)) {
            ops.add(name);
            backboneOps.addOpEntry(name);
        }
    }

    /**
     * Removes the given player from the ops list
     *
     * @param name the player name you want to remove
     */
    public void removePlayer(String name) {
        if (ops.contains(name)) {
            ops.remove(name);
            backboneOps.removeOpEntry(name);
        }
    }

    /**
     * gets the current size of the ops list
     *
     * @return the size
     */
    public int getSize() {
        return ops.size();
    }
}
