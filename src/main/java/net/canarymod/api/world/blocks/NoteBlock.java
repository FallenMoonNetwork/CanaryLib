package net.canarymod.api.world.blocks;


/**
 * Wrap a TileEntityNote etc
 * 
 * @author Chris
 * 
 */
public interface NoteBlock extends ComplexBlock {

    /**
     * Get the current note pitch
     * 
     * @return
     */
    public byte getNote();

    /**
     * Set the note pitch
     * 
     * @param note
     */
    public void setNote(byte note);

    /**
     * Emit a sound from this note block
     */
    public void play();

    /**
     * Returns the instrument for this note block as byte value<br>
     * Wood based blocks = 4 (Bass Guitar)<br>
     * Sand/Gravel blocks = 2 (Snare Drum)<br>
     * Glass blocks = 3 (Clicks / Rimshots))<br>
     * Stone/rocks based blocks = 1 (Bassdrum)<br>
     * all other blocks = 0 (Piano)
     * 
     * @return
     */
    public byte getInstrument();
}
