import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class ChordAgentV1 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments = new ArrayList<>();

    ArrayList<Integer> drumFrequencies;
    String key;

    public ChordAgentV1(ArrayList<Integer> drumFrequenciesIn, String keyIn){
        drumFrequencies = drumFrequenciesIn;
        Resources resources = new Resources();
        if (keyIn.equals("random") || keyIn.equals("r")){
            key = resources.getRandomKey();
        }
        else{
            key = keyIn;
        }
    }
    @Override
    public String makeMusic(int number_of_bars) throws Exception{
        Resources resources = new Resources();

        Random rand_bpm = new Random();
        bpm = rand_bpm.nextInt(130-90+1)+90;
        Instrument instrument = new Instrument(1);
        instruments.add(instrument);

        DrummerAgentV1 drummer = new DrummerAgentV1(bpm, number_of_bars);

        //there is currently 1 instrument in drummer but this allows for functionality if drummer ever changes to multiple instruments
        instruments.addAll(drummer.instruments);



        //2. Create a list of numerals that will represent an abstract chord progression
        //   number of chords should be equal to number_of_bars
        //  *Ideas for intelligence:
        //    - Create a search space with all possible chord progression possibilities
        //    - Gather data about popular chord progressions to use as a heuristic
        //   For now, it will be hardcoded
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

        //determine chord by abstraction of drum pitches
        int chordFrequency = calculateDuration(drumFrequencies);
        for (int i = 0; i < chordInfoProgression.size(); i++) {
            ArrayList<String> currChordNotes = resources.getChord(chordInfoProgression.get(i).root, 4, chordInfoProgression.get(i).inversion, chordInfoProgression.get(i).chordType, "");
            for (int j = 0; j < currChordNotes.size(); j++) {
                for (int k = i; k < number_of_bars; k+=4) {
                    Note currNote = new Note(k * 16, currChordNotes.get(j), chordFrequency, instruments.get(0).getInstrumentId());
                    instruments.get(0).addNote(currNote);
                }
            }
        }
        //TODO: make chord listen to the drummer agent
        return toString();
    }

    private int calculateDuration(ArrayList<Integer> drumFrequencies) {
        //select random method 0-4, where 0 is none and returns a default 16
        Random r = new Random();
        int methodNum = r.nextInt(5);
        if (methodNum == 0){
            return 16;
        } else if (methodNum == 1){
            //returns average of kick drum and snare frequencies
            int add = drumFrequencies.get(2) + drumFrequencies.get(3);
            return add/2;
        } else if (methodNum == 2){
            //returns average of all values of drumFrequencies
            int add = 0;
            for (int i = 0; i < drumFrequencies.size(); i++){
                add+= drumFrequencies.get(i);
            }
            return add/drumFrequencies.size();
        } else if (methodNum == 3){
            /* returns difference between maximum and minimum value; modulo by 16 to increase total frequency in the event
             * of a large distance between minimum and maximum
             */
            return (Collections.max(drumFrequencies) - Collections.min(drumFrequencies))%16;
        } else {
            //returns average of high hat values
            int add = drumFrequencies.get(0) + drumFrequencies.get(1);
            return add/2;
        }
    }

    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
