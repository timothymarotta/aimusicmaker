import java.util.ArrayList;

public class ChordAgentV1 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments;
    String key;

    @Override
    public String makeMusic(int number_of_bars) {

        //Ex: key = "Cmajor"
        String key = Resources.getRandomKey();

        //this assumes there will be one chord per bar
//        ArrayList<String> chordProgression = new ArrayList<>();
//        for(int i=0; i<number_of_bars; i++){
//            chordProgression.add(Resources.getChordProgrssion())
//        }



        return Resources.toString(instruments, bpm);
    }


}
