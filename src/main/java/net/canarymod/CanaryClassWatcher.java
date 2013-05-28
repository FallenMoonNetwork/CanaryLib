package net.canarymod;

import java.util.ArrayList;
import java.util.HashMap;

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
