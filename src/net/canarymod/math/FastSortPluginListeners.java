package net.canarymod.math;

import java.util.ArrayList;

import net.canarymod.plugin.RegisteredPluginListener;

/*
 * @(#)QSortAlgorithm.java      1.3   29 Feb 1996 James Gosling
 *
 * Copyright (c) 1994-1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL or COMMERCIAL purposes and
 * without fee is hereby granted. 
 * 
 */

/**
 * QuickSort implementation by james Gosling and Kevin A. Smith, modified to
 * sort PluginListeners by priority order by the Canary Team
 * 
 * @author James Gosling
 * @author Kevin A. Smith
 * @version @(#)QSortAlgorithm.java 1.3, 29 Feb 1996 extended with TriMedian and
 *          InsertionSort by Denis Ahrens with all the tips from Robert
 *          Sedgewick (Algorithms in C++). It uses TriMedian and InsertionSort
 *          for lists shorts than 4. <fuhrmann@cs.tu-berlin.de>
 */
public class FastSortPluginListeners {
    /**
     * This is a generic version of C.A.R Hoare's Quick Sort algorithm. This
     * will handle arrays that are already sorted, and arrays with duplicate
     * keys.<BR>
     * 
     * If you think of a one dimensional array as going from the lowest index on
     * the left to the highest index on the right then the parameters to this
     * function are lowest index or left and highest index or right. The first
     * time you call this function it will be with the parameters 0, a.length -
     * 1.
     * 
     * @param listener
     *            an integer array
     * @param l
     *            left boundary of array partition
     * @param r
     *            right boundary of array partition
     */
    private static void QuickSort(RegisteredPluginListener listener[], int l, int r) {
        int M = 4;
        int i;
        int j;
        RegisteredPluginListener v;

        if ((r - l) > M) {
            i = (r + l) / 2;
            if (listener[l].getHook().ordinal() > listener[i].getHook().ordinal()) swap(listener, l, i); // Tri-Median Methode!
            if (listener[l].getHook().ordinal() > listener[r].getHook().ordinal()) swap(listener, l, r);
            if (listener[i].getHook().ordinal() > listener[r].getHook().ordinal()) swap(listener, i, r);

            j = r - 1;
            swap(listener, i, j);
            i = l;
            v = listener[j];
            for (;;) {
                while (listener[++i].getHook().ordinal() < v.getHook().ordinal());
                while (listener[--j].getHook().ordinal() > v.getHook().ordinal());
                if (j < i) break;
                swap(listener, i, j);
            }
            swap(listener, i, r - 1);
            // pause(i);
            QuickSort(listener, l, j);
            QuickSort(listener, i + 1, r);
        }
    }

    private static void swap(RegisteredPluginListener a[], int i, int j) {
        RegisteredPluginListener T;
        T = a[i];
        a[i] = a[j];
        a[j] = T;
    }

    private static void InsertionSort(RegisteredPluginListener a[], int lo0, int hi0) {
        int i;
        int j;
        RegisteredPluginListener v;

        for (i = lo0 + 1; i <= hi0; i++) {
            v = a[i];
            j = i;
            while ((j > lo0) && (a[j - 1].getHook().ordinal() > v.getHook().ordinal())) {
                a[j] = a[j - 1];
                // pause(i,j);
                j--;
            }
            a[j] = v;
        }
    }

    public static ArrayList<RegisteredPluginListener> sort(ArrayList<RegisteredPluginListener> listeners) {
        RegisteredPluginListener[] a = new RegisteredPluginListener[listeners.size()];
        for(int i = 0; i < listeners.size(); i++) {
            a[i] = listeners.get(i);
        }
        
        QuickSort(a, 0, a.length - 1);
        InsertionSort(a, 0, a.length - 1);
        listeners.clear();
        for (RegisteredPluginListener reg : a) {
            listeners.add(reg);
        }
        return listeners;
    }
}
