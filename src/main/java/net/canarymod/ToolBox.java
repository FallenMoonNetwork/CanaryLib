package net.canarymod;


import java.util.Arrays;
import java.util.HashSet;

/**
 * Set of miscellaneous tools
 */
public class ToolBox {

    /**
     * Merge 2 arrays. This will just merge two arrays.
     * 
     * @param first
     *            the first array to be merged
     * @param second
     *            the second array to be merged
     * @return array containing all elements of the 2 given ones
     */
    public static <T> T[] arrayMerge(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);

        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * Merge 2 arrays. This will remove duplicates.
     * 
     * @param first
     *            the first array to be merged
     * @param second
     *            the second array to be merged
     * @param template
     *            the array to use as a template for merging the arrays into one
     * @return array containing all elements of the given 2 arrays, minus duplicate entries
     */
    public static <T> T[] safeArrayMerge(T[] first, T[] second, T[] template) {
        HashSet<T> res = new HashSet<T>();

        for (T tFirst : first) {
            res.add(tFirst);
        }
        for (T tSecond : second) {
            res.add(tSecond);
        }
        return res.toArray(template);
    }

    /**
     * If the given string is "null" or null, this method returns null,
     * otherwise it will return the string as it was passed
     * 
     * @param str
     *            the {@link String} to parse
     * @return {@code null} if the {@link String} is {@code null} or is equal to {@code "null"}; the {@link String} value otherwise
     */
    public static String stringToNull(String str) {
        if (str == null) {
            return null;
        }
        if (str.equalsIgnoreCase("null")) {
            return null;
        } else {
            return str;
        }
    }

    /**
     * Round a entity ordinate to a valid block location component
     * This takes into account the rounding issues in negative x/z direction
     * 
     * @param num
     *            the {@code double} value to round to the nearest lower integer
     * @return the rounded number
     */
    public static int floorToBlock(double num) {
        int i = (int) num;

        return num < i ? i - 1 : i;
    }
}
