import java.util.*;

public class Resources {
    private ArrayList<String> noteReference = new ArrayList<>();
    private HashMap<String, List<String>> scales = new HashMap<>();
    public Resources() {
        createNoteReference();
        initializeScales();
    }


    /**
     * Used to convert a key and numeral into a ChordInfo object
     * @param key Ex: "Cmajor"
     * @param numeral Ex: "ii"
     * @returns A ChordInfo object, which holds the rootNote, type of chord, inversion and extension
     */
    public static ChordInfo getChordInfoFromKeyAndNumeral(String key, String numeral) throws Exception{
        boolean numeralIsMajor = isNumeralMajor(numeral);
        String chordType;
        if(numeralIsMajor){
            chordType = "major";
        }
        else{
            chordType = "minor";
        }

        int index;
        if(numeral.toLowerCase().equals("i")){
            index = 0;
        }
        else if(numeral.toLowerCase().equals("ii")){
            index = 1;
        }
        else if(numeral.toLowerCase().equals("iii")){
            index = 2;
        }
        else if(numeral.toLowerCase().equals("iv")){
            index = 3;
        }
        else if(numeral.toLowerCase().equals("v")){
            index = 4;
        }
        else if(numeral.toLowerCase().equals("vi")){
            index = 5;
        }
        else if(numeral.toLowerCase().equals("vii")){
            index = 6;
        }
        else{ //the numeral is not recognized
            throw new Exception("numeral " + numeral + " is not recognized");
        }

        String rootChar;
        if(key.charAt(1) == '#'){
            rootChar = key.substring(0,2);
        }
        else{
            rootChar = key.substring(0,1);
        }

        Resources resources = new Resources();
        String root = resources.getScale(rootChar, chordType).get(index);

        ChordInfo chordInfo = new ChordInfo(root, chordType);
        return chordInfo;
    }

    /**
     * Used to initialize the key of an Agent
     * @return A key Ex: "Cmajor" or "Aminor"
     */
    public String getRandomKey() {
        Random r = new Random();
        int low = 0;
        int high = this.noteReference.size();
        int result = r.nextInt(high-low) + low;
        return noteReference.get(result) + "major";
    }

    private void createNoteReference(){
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
    private void initializeScales(){
        scales.put("C", new ArrayList<>(Arrays.asList("C", "D", "E", "F", "G", "A", "B")));
        scales.put("C#", new ArrayList<>(Arrays.asList("C#", "D#", "F", "F#", "G#", "A#", "C")));
        scales.put("D", new ArrayList<>(Arrays.asList("D", "E", "F#", "G", "A", "B", "C#")));
        scales.put("D#", new ArrayList<>(Arrays.asList("D#", "F", "G", "G#", "A#", "B#", "D")));
        scales.put("E", new ArrayList<>(Arrays.asList("E, F#, G#, A, B, C#, D#".split(", "))));
        scales.put("F", new ArrayList<>(Arrays.asList("F, G, A, A#, C, D, E".split(", "))));
        scales.put("F#", new ArrayList<>(Arrays.asList("F#, G#, A#, B, C#, D#, E#".split(", "))));
        scales.put("G", new ArrayList<>(Arrays.asList("G, A, B, C, D, E, F#".split(", "))));
        scales.put("G#", new ArrayList<>(Arrays.asList("G#, A#, B#, C#, D#, E#, G".split(", "))));
        scales.put("A", new ArrayList<>(Arrays.asList("A, B, C#, D, E, F#, G#".split(", "))));
        scales.put("A#", new ArrayList<>(Arrays.asList("A#, B#, D, D#, E#, G, A".split(", "))));
        scales.put("B", new ArrayList<>(Arrays.asList("B, C#, D#, E, F#, G#, A#".split(", "))));
    }

    public List<String> getScale(String scaleName, String scaleMode){
        if (scaleMode.equals("major")){
            return scales.get(scaleName);
        } else if (scaleMode.equals("minor")){
            List<String> minor = scales.get(scaleName);
            //select the third note in a major scale
            String noteToChange = minor.get(2);
            //find the index of the note in note reference
            int minorIndex = noteReference.indexOf(noteToChange) - 1;
            //replace base third note with minorIndex
            minor.set(2, noteReference.get(minorIndex));
            return minor;
        }
        return null;
    }

    /**
     *
     * @param extension
     * @return
     * @throws Exception
     */
    private ArrayList<Integer> getNoteFromScale(String extension) throws Exception {
        ArrayList<Integer> chordToReturn = new ArrayList<>();
        //adds the base triad form to the returned chord
        chordToReturn.add(0);
        chordToReturn.add(2);
        chordToReturn.add(4);
        //split the extension string by commas to determine any individual extensions
        if (extension.isEmpty()){
            return chordToReturn;
        }
        ArrayList<String> indivExtensions = new ArrayList<>(Arrays.asList(extension.split(",")));
        for (int i = 0; i < indivExtensions.size(); i++){
            String extRequest = indivExtensions.get(i);
            if (extRequest.length() == 1){
                int extNum = Integer.parseInt(extRequest);
                chordToReturn.add((extNum % 7) - 1);
            } else {
                throw new Exception("individual extensions should only be non-zero positive numbers. Do not add any other characters.");
            }
        }
        return null;
    }

    /**
     *
     * @param extension
     * @return
     * @throws Exception
     */
    private ArrayList<Integer> getIndicesForChord(String extension) throws Exception {
        ArrayList<Integer> chordToReturn = new ArrayList<>();
        //adds the base triad form to the returned chord
        chordToReturn.add(0);
        chordToReturn.add(2);
        chordToReturn.add(4);
        //split the extension string by commas to determine any individual extensions
        if (extension.isEmpty()){
            return chordToReturn;
        }
        ArrayList<String> indivExtensions = new ArrayList<>(Arrays.asList(extension.split(",")));
        for (int i = 0; i < indivExtensions.size(); i++){
            String extRequest = indivExtensions.get(i);
            if (extRequest.length() == 1){
                int extNum = Integer.parseInt(extRequest);
                chordToReturn.add((extNum % 7) - 1);
            } else {
                throw new Exception("individual extensions should only be non-zero positive numbers. Do not add any other characters.");
            }
        }
        return null;
    }

    /**
     *
     * @param notes
     * @param octave
     * @param inversion
     * @return
     */
    private ArrayList<String> assignOctave(ArrayList<String> notes, int octave, int inversion){
        if (notes.size() == 3){
            if (inversion == 0){
                notes.set(0, notes.get(0) + octave);
                if ((getNotePosition(notes.get(1)) < getNotePosition(notes.get(0))) && octave < 7){
                    notes.set(1, notes.get(1) + octave + 1);
                } else {
                    notes.set(1, notes.get(1) + octave);
                }
                if ((getNotePosition(notes.get(2)) < getNotePosition(notes.get(0))) && octave < 7){
                    notes.set(2, notes.get(2) + octave + 1);
                } else {
                    notes.set(2, notes.get(2) + octave);
                }
                return notes;
            } else if (inversion == 1){
                notes.set(1, notes.get(1) + octave);
                if ((getNotePosition(notes.get(0)) < getNotePosition(notes.get(1))) && octave < 7){
                    notes.set(0, notes.get(0) + octave + 1);
                } else {
                    notes.set(0, notes.get(0) + octave);
                }
                if ((getNotePosition(notes.get(2)) < getNotePosition(notes.get(1))) && octave < 7){
                    notes.set(2, notes.get(2) + octave + 1);
                } else {
                    notes.set(2, notes.get(2) + octave);
                }
                return notes;
            } else if (inversion == 2){
                notes.set(2, notes.get(2) + octave);
                if ((getNotePosition(notes.get(1)) < getNotePosition(notes.get(2))) && octave < 7){
                    notes.set(1, notes.get(1) + octave + 1);
                } else {
                    notes.set(1, notes.get(1) + octave);
                }
                if ((getNotePosition(notes.get(0)) < getNotePosition(notes.get(2))) && octave < 7){
                    notes.set(0, notes.get(0) + octave + 1);
                } else {
                    notes.set(0, notes.get(0) + octave);
                }
                return notes;
            }
        } else {
            notes.set(0, notes.get(0) + octave);
            for (int i = 0; i < notes.size(); i++){
                if ((getNotePosition(notes.get(i)) < getNotePosition(notes.get(0))) && octave < 7){
                    notes.set(i, notes.get(i) + octave + 1);
                } else {
                    notes.set(i, notes.get(i) + octave);
                }
            }
            return notes;
        }
        return null;
    }

    public int getNotePosition(String note){
        /*
        Function acts as a getter for noteReference; should be used for comparison of notes to determine chord placement
        and inversion positions. Returns the index of a given note in an ArrayList. If the passed in string is not in
        noteReference, returns -1.
         */
        return noteReference.indexOf(note);
    }
    public ArrayList<String> getChord(String rootNote, int octave, int inversion, String chordType, String extension) throws Exception {
        //TODO given parameters, create chord arrayList of Note objects and return to caller
        //return a string of note-octave pairs (i.e. A3, C6, E7)
        //if no chord type known, return null and throw error No chord found
        //reject improper inversion type / deal w/ octave only if valid octave # (1-7)
        //chordType - check iof present in hashmap
        if(inversion > 2){
            throw new ArithmeticException("inversion # invalid");
        }
        if(octave > 7 || octave < 1){
            throw new ArithmeticException("octave # invalid");
        }//throw exceptions for extensions
        //get major or minor scale given the root note
        List<String> validNotes = getScale(rootNote, chordType);
        //gets indexes for a chord to be selected from validNotes; assumes a triad as a base
        ArrayList<Integer> chordNotePositions = getIndicesForChord(extension);
        //selects notes from validNotes based on indices from chordNotePositions
        ArrayList<String> chord = new ArrayList<>();
        assert chordNotePositions != null;
        for(int i = 0; i<chordNotePositions.size(); i++){
            chord.add(validNotes.get(chordNotePositions.get(i)));
        }
        return assignOctave(chord,octave,inversion);
    }

    private int minor (String note){
        //TODO return index of middle note in reference array shifted left by 1

        return -1;
    }

    public static String getHiHatPitch(){
        String allPitches[] = new String[]{"C#7", "A#6", "E6", "D#6", "A#5", "A5", "D5", "C5", "B4", "A4", "G4", "F#4", "D#4", "A#", "G#3", "F#3", "D#3", "C#3", "G2", "E2"};
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

    private static boolean isNumeralMajor(String numeral){

        //convert String to char array
        char[] charArray = numeral.toCharArray();

        for(int i=0; i < charArray.length; i++){

            //if any character is not in upper case, return false
            if( !Character.isUpperCase( charArray[i] ))
                return false;
        }

        return true;
    }

}
