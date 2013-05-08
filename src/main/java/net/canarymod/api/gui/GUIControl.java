package net.canarymod.api.gui;

public class GUIControl {

    public final GUI gui;

    public GUIControl(GUI gui) {
        this.gui = gui;
    }

    /**
     * Used when the GUI is to be replaced.
     * Should be used to Close your GUI with out stopping the server
     */
    public void Close() {
        gui.closeWindow();
    }

}
