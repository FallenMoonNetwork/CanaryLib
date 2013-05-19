package net.canarymod;


import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;


/**
 * Canary Class Loader
 * <p>
 * Used as a master Class Loader for plugins<br>
 * Adding and Removing the URL path to the jar files.
 * 
 * @author Jason (darkdiplomat)
 */
public final class CanaryClassLoader extends URLClassLoader {

    public CanaryClassLoader(URL[] urls, ClassLoader loader) {
        super(urls, loader);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    @SuppressWarnings("rawtypes")
    public void removeURL(URL url) {
        Class<?> clazz = URLClassLoader.class;
        Field ucp;

        try {
            ucp = clazz.getDeclaredField("ucp");
            ucp.setAccessible(true);
            Object urlClassPath = ucp.get(this);
            Field lmap = urlClassPath.getClass().getDeclaredField("lmap");
            lmap.setAccessible(true);
            Object urls = lmap.get(urlClassPath);
            ((HashMap) urls).remove(url);
        } catch (SecurityException e) {
            Canary.logStackTrace(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            Canary.logStackTrace(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            Canary.logStackTrace(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            Canary.logStackTrace(e.getMessage(), e);
        }
    }
}
