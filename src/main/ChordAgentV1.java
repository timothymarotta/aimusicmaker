import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChordAgentV1 implements AgentIF {
    @Override
    public String makeMusic(int number_of_bars) throws Exception{
        Resources resources = new Resources();

        //1. Set a random key
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
        for(int i=0; i<chordInfoProgression.size(); i++){
            ChordInfo chordInfo = chordInfoProgression.get(i);
            //TODO: getChord is currently unimplemented
            resources.getChord(chordInfo.root, 4, chordInfo.inversion, chordInfo.chordType);
        }
        return null;
    }
}
