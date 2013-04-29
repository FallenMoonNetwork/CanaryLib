package net.canarymod.plugin;


import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.jar.JarFile;

import net.canarymod.Canary;


/**
 * Class loader used to load classes dynamically. This also closes the jar so we
 * can reload the plugin.
 *
 * @author James
 *
 */
public class CanaryClassLoader extends URLClassLoader {
    private static HashSet<CanaryClassLoader> loaders = new HashSet<CanaryClassLoader>();

    public CanaryClassLoader(URL[] urls, ClassLoader loader) {
        super(urls, loader);
        loaders.add(this);
    }

    public void close() {
        Class<?> clazz = URLClassLoader.class;
        Field ucp;

        try {
            ucp = clazz.getDeclaredField("ucp");
            ucp.setAccessible(true);
            Object urlClassPath = ucp.get(this);
            Field loaders = urlClassPath.getClass().getDeclaredField("loaders");

            loaders.setAccessible(true);
            Object classpaths = loaders.get(urlClassPath);

            for (Object classpath : ((java.util.Collection<?>) classpaths).toArray()) {
                try {
                    Field loader = classpath.getClass().getDeclaredField("jar");

                    loader.setAccessible(true);
                    JarFile jarfile = (JarFile) loader.get(classpath);

                    jarfile.close();
                } catch (Throwable t) {
                    Canary.logStackTrace("Failed to close a Plugin!", t);
                }
            }
        } catch (SecurityException e) {
            Canary.logStackTrace(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            Canary.logStackTrace(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            Canary.logStackTrace(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
        loaders.remove(this);
    }

    /**
     * {@inheritDoc}
     *
     * This method is overridden to check other plugin class loaders as well.
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = this.tryLoadClass(name);
        if (clazz != null)
            return clazz;

        for (CanaryClassLoader cl : loaders) {
            clazz = cl.tryLoadClass(name);
            if (clazz != null)
                return clazz;
        }

        throw new ClassNotFoundException(name);
    }

    private Class<?> tryLoadClass(String name) {
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public void addURL(URL url){
        super.addURL(url);
    }
}
