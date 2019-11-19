import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChordAgentV1 implements AgentIF {
    @Override
    public String makeMusic(int number_of_bars) {

        //1. Set a random key
        String key = Resources.getRandomKey();

        //2. Create a list of numerals that will represent an abstract chord progression
        //   number of chords should be equal to number_of_bars
        //   For now, it will be hardcoded
        List<String> numerals  = Arrays.asList("I", "V", "ii", "IV");

        //3. turn numerals into a list of lists of pitches
        ArrayList<ArrayList<String>> chordProgression = new ArrayList();
        for(int i=0; i<numerals.size(); i++){
            ArrayList<String> newChord = Resources.getPitchesFromNumeral(key, numerals.get(i));
            chordProgression.add(newChord);
        }

        //4. Create Note objects for the instrument based on the pitches from chordProgression
        //   This is where the rhythm/grove is "creatively" decided.
        //   To keep this agent simple, the first chord will be played in the first bar, second chord in the second bar, etc...

        return null;
    }
}
