import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChordAgentV1 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments = new ArrayList<>();

    ArrayList<Integer> drumFrequencies;
    String key;

    /**
     * Constructor for ChordV1 Agent
     * keyIn- the key that the music will be in
     * bpm- the beats per minute, or the tempo
     */
    public ChordAgentV1(String keyIn, int bpmIn){
        Resources resources = new Resources();
        if (keyIn.equals("r")){
            key = resources.getRandomKey();
        }
        else{
            key = keyIn;
        }
        bpm = bpmIn;
    }

    /**
     * Picks a chord progression and places a chord at the beginning of every measure
     * @param number_of_bars is the number of measures in the song Ex: 8
     * @returns A string in the format that the environment needs. The returned string will be written in a file once returned.
     */
    @Override
    public String makeMusic(int number_of_bars) throws Exception{
        Resources resources = new Resources();

        Instrument instrument = new Instrument(1);
        instruments.add(instrument);


        //2. Create a list of numerals that will represent an abstract chord progression
        //   number_of_bars should be divisible to number of chords

        List<String> numerals  = resources.getRandomHardcodedChordProgression();

        //3. turn numerals into a list of lists of pitches
        ArrayList<ChordInfo> chordInfoProgression = new ArrayList();
        for(int i=0; i<numerals.size(); i++){
            ChordInfo newChord = Resources.getChordInfoFromKeyAndNumeral(key, numerals.get(i));
            chordInfoProgression.add(newChord);
        }

        //4. Create Note objects for the instrument based on the pitches from chordProgression
        //   This is where the rhythm/grove is "creatively" decided.
        //   To keep this agent simple, the first chord will be played in the first bar, second chord in the second bar, etc...
        //  *This is an opportunity for intelligently listening to the drummerAgent*
        for (int i = 0; i < chordInfoProgression.size(); i++) {
            ArrayList<String> currChordNotes = resources.getChord(chordInfoProgression.get(i).root, 4, chordInfoProgression.get(i).inversion, chordInfoProgression.get(i).chordType, "");
            for (int j = 0; j < currChordNotes.size(); j++) {
                for (int k = i; k < number_of_bars; k+=4) {
                    Note currNote = new Note(k * 16, currChordNotes.get(j), 16, instruments.get(0).getInstrumentId());
                    instruments.get(0).addNote(currNote);
                }
            }
        }
        //TODO: make chord listen to the drummer agent
        return toString();
    }

    /**
     * Passes list of instruments and bpm to the resources's toString
     * @returns A string in the format that the environment can read
     */
    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
