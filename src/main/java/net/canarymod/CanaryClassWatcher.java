package net.canarymod;

import net.canarymod.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Canary Class Watcher
 * <p/>
 * Designed to allow plugins to share classes between each other.<br>
 * NOTE: If a class isn't loaded, this will load the class from the first ClassLoader that has the class.<br>
 * It could be an incorrect class or improperly initialized. Plugin devs should program with this in mind.
 *
 * @author Jason (darkdiplomat)
 */
final class CanaryClassWatcher {
    private final HashMap<CanaryClassLoader, ArrayList<Class<?>>> loadedClasses = new HashMap<CanaryClassLoader, ArrayList<Class<?>>>();

    /**
     * Finds a loaded {@link Class} from any of the {@link Plugin}'s {@link CanaryClassLoader}
     *
     * @param name
     *         the name of the {@link Class}
     *
     * @return the {@link Class} if found; {@code null} otherwise
     */
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

    /**
     * Loads a {@link Class} from the first loader that contains the class
     *
     * @param name
     *         the name of the {@link Class} to be loaded
     *
     * @return the {@link Class} if found; {@code null} otherwise
     */
    private synchronized final Class<?> loadClass(String name) {
        String nameTemp = name.replace('.', '/').concat(".class");
        for (CanaryClassLoader loader : loadedClasses.keySet()) {
            if (loader.getResource(nameTemp) != null) {
                try {
                    Class<?> clazz = loader.loadClass(name);
                    addClass(loader, clazz);
                    return clazz;
                }
                catch (ClassNotFoundException e) {
                    // Realistically this shouldn't happen here since we pre-checked if the jar has the resource
                    // But then again it is Java
                }
            }
        }
        return null;
    }

    /**
     * Adds a {@link Class} to the list of loaded classes
     *
     * @param loader
     *         the {@link CanaryClassLoader} the {@link Class} is from
     * @param clazz
     *         the {@link Class} to be added
     */
    synchronized final void addClass(CanaryClassLoader loader, Class<?> clazz) {
        if (!loadedClasses.containsKey(loader)) {
            loadedClasses.put(loader, new ArrayList<Class<?>>());
        }
        loadedClasses.get(loader).add(clazz);
    }

    /**
     * Removes a {@link CanaryClassLoader} and all associated {@link Class}es
     *
     * @param loader
     *         the {@link CanaryClassLoader} to remove
     */
    synchronized final void removeLoader(CanaryClassLoader loader) {
        if (loadedClasses.containsKey(loader)) {
            loadedClasses.get(loader).clear();
            loadedClasses.remove(loader);
        }
    }
}
