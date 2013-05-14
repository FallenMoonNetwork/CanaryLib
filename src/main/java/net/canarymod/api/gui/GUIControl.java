package net.canarymod.api.gui;

public interface GUIControl {

    /**
     * Used when the GUI is to be replaced.
     * Should be used to Close your GUI with out stopping the server
     */
    public void closeWindow();

    /**
     * Called to start the GUI
     * @return
     */
    public void start();

}
