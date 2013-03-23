package net.canarymod.plugin;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.jar.JarFile;

import net.canarymod.Logman;

/**
 * Class loader used to load classes dynamically. This also closes the jar so we
 * can reload the plugin.
 *
 * @author James
 *
 */
class CanaryClassLoader extends URLClassLoader {
    private static HashSet<Class<? extends ClassLoader>> classes = new HashSet<Class<? extends ClassLoader>>();

    public CanaryClassLoader(URL[] urls, ClassLoader loader) {
        super(urls, loader);
        classes.add(loader.getClass());
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
                    Logman.logStackTrace("Failed to close a Plugin!", t);
                }
            }
        }
        catch (SecurityException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        catch (NoSuchFieldException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        catch (IllegalArgumentException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        catch (IllegalAccessException e) {
            Logman.logStackTrace(e.getMessage(), e);
        }
        return;
    }
}