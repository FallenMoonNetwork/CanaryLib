package net.canarymod.plugin;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class loader used to load classes dynamically. This also closes the jar so we
 * can reload the plugin.
 * 
 * @author James
 * 
 */
public class CanaryClassLoader extends URLClassLoader {

    public CanaryClassLoader(URL[] urls, ClassLoader loader) {
        super(urls, loader);
    }

    @SuppressWarnings("rawtypes")
    public void close() {
        try {
            Class<?> clazz = java.net.URLClassLoader.class;
            java.lang.reflect.Field ucp = clazz.getDeclaredField("ucp");

            ucp.setAccessible(true);
            Object sun_misc_URLClassPath = ucp.get(this);
            java.lang.reflect.Field loaders = sun_misc_URLClassPath.getClass().getDeclaredField("loaders");

            loaders.setAccessible(true);
            Object java_util_Collection = loaders.get(sun_misc_URLClassPath);

            for (Object sun_misc_URLClassPath_JarLoader : ((java.util.Collection) java_util_Collection).toArray()) {
                try {
                    java.lang.reflect.Field loader = sun_misc_URLClassPath_JarLoader.getClass().getDeclaredField("jar");

                    loader.setAccessible(true);
                    Object java_util_jar_JarFile = loader.get(sun_misc_URLClassPath_JarLoader);

                    ((java.util.jar.JarFile) java_util_jar_JarFile).close();
                } catch (Throwable t) {
                    // if we got this far, this is probably not a JAR loader so
                    // skip it
                }
            }
        } catch (Throwable t) {
            // Probably not a Sun (correct: Oracle) VM.
        }
        return;
    }
}
