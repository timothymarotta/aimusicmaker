import java.util.ArrayList;

public class ConductorAgentBasic implements AgentIF {

    int bpm;
    String key;
    ArrayList<Instrument> instruments = new ArrayList<Instrument>();

    public ConductorAgentBasic(int bpmIn, String keyIn){
        Resources resources = new Resources();
        if (keyIn.equals("random") || keyIn.equals("r")){
            key = resources.getRandomKey();
        }
        else{
            key = keyIn;
        }
        bpm = bpmIn;
    }

    @Override
    public String makeMusic(int number_of_bars) throws Exception {

        DrummerAgentV1 drummer = new DrummerAgentV1(bpm, number_of_bars);

        ChordAgentV1 chords = new ChordAgentV1(key, bpm);
        chords.makeMusic(number_of_bars);
        for(int i = 0; i < drummer.instruments.size(); i++){
            instruments.add(drummer.instruments.get(i));
        }
        for(int i = 0; i < chords.instruments.size(); i++){
            instruments.add(chords.instruments.get(i));
        }
        return toString();
    }

    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
