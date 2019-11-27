import java.util.*;

public class Resources {
    private ArrayList<String> noteReference = new ArrayList<String>();
    private HashMap<String, ArrayList> chords = new HashMap<>();
    public Resources() {
        createNoteReference();
        createChordReference();
    }


    /**
     * Used to convert a key and numeral into a list of pitches
     * @param key Ex: "Cmajor"
     * @param numeral Ex: "ii"
     * @returns A ChordInfo object, which holds the rootNote, type of chord, inversion and extension
     */
    public static ArrayList<String> getPitchesFromNumeral(String key, String numeral) {

        return null;
    }

    /**
     * Used to initialize the key of an Agent
     * @return A key Ex: "Cmajor" or "Aminor"
     */
    public static String getRandomKey() {
        //TODO
        return null;
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

    public static String getHiHatPitch(){
        String allPitches[] = new String[]{"C#7", "A#6", "E6", "D#6", "C#", "A#5", "A5", "D5", "C5", "B4", "A4", "G4", "F#4", "D#4", "C#4", "A#", "G#3", "F#3", "D#3", "C#3", "G2", "E2"};
        List<String> list = Arrays.asList(allPitches);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static String getSnarePitch(){
        String allPitches[] = new String[]{"E3", "D3"};
        List<String> list = Arrays.asList(allPitches);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static String getKickPitch(){
        String allPitches[] = new String[]{"F3", "B2", "C3", "G3", "A3", "B3", "C4", "D4", "E5", "D#7", "D7"};
        List<String> list = Arrays.asList(allPitches);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static String getMiscellaniousPitch(){
        String allPitches[] = new String[]{"C7", "B6", "A6", "G6", "F#6", "C6", "B5", "A#4", "F4", "D#3", "A#2", "F#2", "F2", "E2"};
        List<String> list = Arrays.asList(allPitches);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static String toString(ArrayList<Instrument> instruments, int bpm){
        assert instruments.size() > 0;
        String toReturn = "{\"instruments\":{";

        //Instrument info
        for(int i=0; i<instruments.size(); i++){
            Instrument currInstrument = instruments.get(i);
            toReturn = toReturn + "\"" + currInstrument.instrumentId + "\":{\"volume\":" + currInstrument.volume + ",\"delay\":" + currInstrument.delay + ",\"reverb\":" + currInstrument.reverb + "}";
            if (i <instruments.size()-1){
                toReturn += ",";
            }
        }
        toReturn = toReturn + "}}|" + bpm + "|";

        //Note Info
        for(int i=0; i<instruments.size(); i++){
            ArrayList<Note> currNotes = instruments.get(i).notes;
            for(int j=0; j<currNotes.size(); j++){
                toReturn = toReturn + currNotes.get(j).startPosition + " " + currNotes.get(j).pitch + " " + currNotes.get(j).duration + " " + currNotes.get(j).instrumentId + ";";
            }
        }
        return toReturn;
    }

    public static int getRandomInstrumentID(){
        Integer allInstruments[] = new Integer[]{0, 8, 17, 25, 26, 2, 31, 19, 34, 21, 1, 4, 5, 29, 32, 35, 38, 22, 33, 2, 6, 7, 13, 14, 15, 16, 9, 10, 11, 12, 18, 20, 23};
        List<Integer> list = Arrays.asList(allInstruments);
        Random rand = new Random();
        Integer id = list.get(rand.nextInt(list.size()));
        return id.intValue();
    }

}
