import java.util.ArrayList;
import java.util.Random;

public class AgentV2 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments;

    public AgentV2(int bpmIn){
        bpm = bpmIn;
        instruments = new ArrayList<>();
    }

    public AgentV2(){
        //bpm is chosen randomly between 90 and 180
        Random r = new Random();
        int minBpm = 90;
        int maxBpm = 180;
        bpm = r.nextInt(maxBpm-minBpm+1)+minBpm;

        instruments = new ArrayList<>();
    }

    public void addInstrument(Instrument instrumentIn){
        instruments.add(instrumentIn);
    }
    public void addInstrument(int instrumentId, int volume, boolean delay, boolean reverb){
        //TODO make sure new instrument id is unique!
        Instrument newInstrument = new Instrument(instrumentId, volume, delay, reverb);
        instruments.add(newInstrument);
    }

    @Override
    public String makeMusic(int number_of_bars) {
        //TODO music generation goes here!

        return toString();
    }

    public String toString(){
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
}
