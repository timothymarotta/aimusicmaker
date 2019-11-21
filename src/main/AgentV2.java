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
        //bpm is chosen randomly between 90 and 130
        Random r = new Random();
        int minBpm = 80;
        int maxBpm = 130;
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

        return Resources.toString(instruments, bpm);
    }

    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
