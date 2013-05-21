package net.canarymod;


import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.jar.JarFile;


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
        Canary.logDebug("Adding URL: " + url.toString());
        super.addURL(url);
    }

    @SuppressWarnings("rawtypes")
    public void removeURL(URL url) {
        Canary.logDebug("Removing URL: " + url.toString());
        Class<?> clazz = URLClassLoader.class;
        Field ucp;

        try {
            ucp = clazz.getDeclaredField("ucp");
            ucp.setAccessible(true);
            Object urlClassPath = ucp.get(this);
            Field lmap = urlClassPath.getClass().getDeclaredField("lmap");
            lmap.setAccessible(true);
            Object urls = lmap.get(urlClassPath);
            // This shit does something weird to the URL so lets do a bit of matching to find the right one based
            // on what we know about URL and use that for removal
            Object realUrl = null;
            for (Object urlTest : ((HashMap) urls).keySet()) {
                if (urlTest.toString().replace("file:///", "").equals(url.toString().replace("file:/", ""))) {
                    realUrl = urlTest;
                    break;
                }
            }
            ((HashMap) urls).remove(realUrl);
            Field path = urlClassPath.getClass().getDeclaredField("path");
            path.setAccessible(true);
            Object paths = path.get(urlClassPath);
            Canary.logDebug("" + ((ArrayList) paths).remove(url));
            closeJar(url, urlClassPath);
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

    private final void closeJar(URL url, Object urlClassPath) {
        try {
            Field loaders = urlClassPath.getClass().getDeclaredField("loaders");

            loaders.setAccessible(true);
            Object classpaths = loaders.get(urlClassPath);
            @SuppressWarnings("unchecked")
            Iterator<Object> cpIter = (Iterator<Object>) ((java.util.Collection<?>) classpaths).iterator();

            while (cpIter.hasNext()) {
                Object classpath = cpIter.next();
                try {
                    Field loader = classpath.getClass().getDeclaredField("jar");
                    loader.setAccessible(true);
                    JarFile jarfile = (JarFile) loader.get(classpath);
                    Canary.logDebug("JarName: " + jarfile.getName() + " URL: " + url.toString().replace("file:", ""));
                    if (jarfile.getName().equals(url.toString().replace("file:", ""))) {
                        Canary.logDebug("Closing Jar for: " + url.toString());
                        jarfile.close(); // Close Jar
                        cpIter.remove(); // Remove path
                        break;
                    }
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
    }
}
