import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChordAgentV1 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments = new ArrayList<>();

    @Override
    public String makeMusic(int number_of_bars) throws Exception{
        Resources resources = new Resources();

        Random rand_bpm = new Random();
        bpm = rand_bpm.nextInt(130-90+1)+90;
        Instrument instrument = new Instrument(1);
        instruments.add(instrument);

        DrummerAgentV1 drummer = new DrummerAgentV1(bpm, number_of_bars);

        //there is currently 1 instrument in drummer but this allows for functionality if drummer ever changes to multiple instruments
        for(int i = 0; i < drummer.instruments.size(); i++){
            instruments.add(drummer.instruments.get(i));
        }


        //1. Set a random key
        // *currently set to only make major keys!
        String key = resources.getRandomKey();

        //2. Create a list of numerals that will represent an abstract chord progression
        //   number of chords should be equal to number_of_bars
        //  *Ideas for intelligence:
        //    - Create a search space with all possible chord progression possibilities
        //    - Gather data about popular chord progressions to use as a heuristic
        //   For now, it will be hardcoded
        List<String> numerals  = Arrays.asList("I", "V", "ii", "IV");

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

    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
