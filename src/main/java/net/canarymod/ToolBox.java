package net.canarymod;

import java.util.Arrays;
import java.util.HashSet;

public class ToolBox {

    /**
     * Returns the closest positive float to 0 this machine can compute.
     * @return
     */
    public float getFloatEpsilon() {
        float machEps = 1.0f;
        do {
            machEps /= 2.0f;
        } while ((float) (1.0 + (machEps / 2.0)) != 1.0);

        return machEps;
    }

    /**
     * Merge 2 arrays. This will just merge two arrays.
     * @param first
     * @param second
     * @return array containing all elements of the 2 given ones
     */
    public static <T> T[] arrayMerge(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * Merge 2 arrays. This will remove duplicates.
     * @param first
     * @param second
     * @param template
     * @return array containing all elements of the given 2 arrays, minus duplicate entries
     */
    public static  <T> T[] safeArrayMerge(T[] first, T[] second, T[] template) {
        HashSet<T> res = new HashSet<T>();
        for(T tFirst : first) {
            res.add(tFirst);
        }
        for(T tSecond : second) {
            res.add(tSecond);
        }
        return res.toArray(template);
    }
}
