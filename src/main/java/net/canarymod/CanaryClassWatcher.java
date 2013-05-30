package net.canarymod;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Canary Class Watcher
 * <p>
 * Designed to allow plugins to share classes between each other.<br>
 * NOTE: If a class isn't loaded, this will load the class from the first ClassLoader that has the class.<br>
 * It could be an incorrect class or improperly initialized. Plugin devs should program with this in mind.
 * 
 * @author Jason (darkdiplomat)
 */
final class CanaryClassWatcher {
    private final HashMap<CanaryClassLoader, ArrayList<Class<?>>> loadedClasses = new HashMap<CanaryClassLoader, ArrayList<Class<?>>>();

    synchronized final Class<?> findLoadedClass(String name) {
        for (ArrayList<Class<?>> classes : loadedClasses.values()) {
            for (Class<?> clazz : classes) {
                if (clazz.getName().equals(name)) {
                    return clazz;
                }
            }
        }
        return loadClass(name); // ClassNotFound, attempt to load it

    }

    private synchronized final Class<?> loadClass(String name) {
        String nameTemp = name.replace('.', '/');
        for (CanaryClassLoader loader : loadedClasses.keySet()) {
            if (loader.getResource(nameTemp) != null) {
                try {
                    Class<?> clazz = loader.loadClass(name);
                    addClass(loader, clazz);
                    return clazz;
                } catch (ClassNotFoundException e) {
                    // Realistically this shouldn't happen here since we pre-checked if the jar has the resource
                    // But then again it is Java
                }
            }
        }
        return null;
    }

    synchronized final void addClass(CanaryClassLoader loader, Class<?> clazz) {
        if (!loadedClasses.containsKey(loader)) {
            loadedClasses.put(loader, new ArrayList<Class<?>>());
        }
        loadedClasses.get(loader).add(clazz);
    }

    synchronized final void removeLoader(CanaryClassLoader loader) {
        if (loadedClasses.containsKey(loader)) {
            loadedClasses.get(loader).clear();
            loadedClasses.remove(loader);
        }
    }
}
