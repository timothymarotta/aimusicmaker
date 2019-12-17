import java.util.ArrayList;

public class ConductorAgentBasic implements AgentIF {

    int bpm;
    String key;
    ArrayList<Instrument> instruments = new ArrayList<Instrument>();

    /**
     * Constructor for Basic Conductor Agent
     * keyIn- the key that the music will be in
     * bpm- the beats per minute, or the tempo
     */
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

    /**
     * Combines work of Drum agent and Chord Agent. Calls drum agent and then the chord agent.
     * @param number_of_bars is the number of measures in the song Ex: 8
     * @returns A string in the format that the environment needs. This string will have all of the notes and instruments from the chord and drum agents. The returned string will be written in a file once returned.
     */
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

    /**
     * Passes list of instruments and bpm to the resources's toString
     * @returns A string in the format that the environment can read
     */
    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
