package net.canarymod.database;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import net.canarymod.Canary;
import net.canarymod.CanaryClassLoader;
import net.canarymod.database.exceptions.DatabaseException;
import net.visualillusionsent.utils.PropertiesFile;
import net.visualillusionsent.utils.UtilityException;

/**
 * Checks a database folder in CanaryMods root folder for
 * external Database Implementations and loads them
 *
 * @author chris
 */
public class DatabaseLoader {
    /**
     * Scans database folder, loads all valid databases and registers them
     * at Database.Type. This must be the first bootstrapping step,
     * as all other steps require a functional database.
     * This also means this must not make use of anything that isn't loaded already
     */
    public static void load() {
        File dbFolder = new File("databases/");
        if (!dbFolder.exists()) {
            dbFolder.mkdirs();
        }
        for (File file : dbFolder.listFiles()) {
            if (!file.getName().endsWith(".jar")) {
                continue;
            }
            CanaryClassLoader loader;
            try {
                loader = new CanaryClassLoader(file.toURI().toURL(), Thread.currentThread().getContextClassLoader());
            }
            catch (MalformedURLException ex) {
                Canary.logStacktrace("Exception while loading database jar", ex);
                return;
            }
            PropertiesFile inf = new PropertiesFile(file.getAbsolutePath(), "Canary.inf");
            try {
                String mainclass = inf.getString("main-class");
                String dbName = inf.getString("database-name");
                Class<?> dbClass = loader.loadClass(mainclass);
                Method m = dbClass.getMethod("getInstance", new Class[0]);
                Database db = (Database) m.invoke(dbClass, new Object[0]);
                if (db != null) {
                    Database.Type.registerDatabase(dbName, db);
                }
            }
            catch (UtilityException e) {
                Canary.logStacktrace("Could not find databases mainclass", e);
                return;
            }
            catch (ClassNotFoundException e) {
                Canary.logStacktrace("Could not find databases mainclass", e);
            }
            catch (IllegalAccessException e) {
                Canary.logStacktrace("Could not create database", e);
            }
            catch (DatabaseException e) {
                Canary.logStacktrace("Could not add database", e);
            }
            catch (SecurityException e) {
                Canary.logStacktrace(e.getMessage(), e);
            }
            catch (NoSuchMethodException e) {
                Canary.logStacktrace("Database does not contain a static getInstance() method!", e);
            }
            catch (IllegalArgumentException e) {
                Canary.logStacktrace(e.getMessage(), e);
            }
            catch (InvocationTargetException e) {
                Canary.logStacktrace(e.getMessage(), e);
            }
        }
    }
}
