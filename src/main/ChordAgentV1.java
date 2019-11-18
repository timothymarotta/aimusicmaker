import java.util.ArrayList;
import java.util.Arrays;

public class ChordAgentV1 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments;
    String key;

    @Override
    public String makeMusic(int number_of_bars) {

        //set the key Ex: key = "Cmajor"
        String key = Resources.getRandomKey();

        //generate a chord progression Ex: {"I", 'iii", "V" 'IV"}
        //for now, it will be hardcoded

        String cp[] = new String[]{"I", "iii", "V", "IV"};
        ArrayList<String> chordProgression = new ArrayList<String>(Arrays.asList("I", "iii", "V", "IV"));

        //this assumes there will be one chord per bar
//        ArrayList<String> chordProgression = new ArrayList<>();
//        for(int i=0; i<number_of_bars; i++){
//            chordProgression.add(Resources.getChordProgrssion())
//        }



        return Resources.toString(instruments, bpm);
    }


}
