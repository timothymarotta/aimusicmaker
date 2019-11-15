import java.util.ArrayList;
import java.util.HashMap;

public class Resources {
    private ArrayList<String> noteReference = new ArrayList<String>();
    private HashMap<String, ArrayList> chords = new HashMap<>();
    public Resources() {
        createNoteReference();
        createChordReference();
    }

    private void createNoteReference(){
        //TODO find a better way to initialize the note reference ArrayList?
        noteReference.add("C");
        noteReference.add("C#");
        noteReference.add("D");
        noteReference.add("D#");
        noteReference.add("E");
        noteReference.add("F");
        noteReference.add("F#");
        noteReference.add("G");
        noteReference.add("G#");
        noteReference.add("A");
        noteReference.add("A#");
        noteReference.add("B");
    }
    private void createChordReference() {

    }

    public int getNotePosition(String note){
        /*
        Function acts as a getter for noteReference; should be used for comparison of notes to determine chord placement
        and inversion positions. Returns the index of a given note in an ArrayList. If the passed in string is not in
        noteReference, returns -1.
         */
        return noteReference.indexOf(note);
    }
    public ArrayList getChord(String chordName, int octave, int inversion, boolean minor){
        //TODO given parameters, create chord arrayList of Note objects and return to caller
        return null;
    }

    private int minor (String note){
        //TODO return index of middle note in reference array shifted left by 1
        return -1;
    }
}
