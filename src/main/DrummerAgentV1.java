import java.util.ArrayList;
import java.util.Random;

public class DrummerAgentV1 implements AgentIF {

    int bpm;
    Instrument drum;
    ArrayList<Instrument> instruments = new ArrayList<>();

// electric -31 / standard- 2
    public DrummerAgentV1(int bpm){
        this.bpm = bpm;
        drum = new Instrument(2);
        makeMusic(4);
        instruments.add(drum);
    }

    @Override
    public String makeMusic(int number_of_bars) {
        int songLength = number_of_bars * 16;
        String hiHatPitch = Resources.getHiHatPitch();
        String snarePitch = Resources.getSnarePitch();
        String kickPitch = Resources.getSnarePitch();
        String misc = Resources.getMiscellaniousPitch();

        Random r = new Random();
        int hiHatFrequency = r.nextInt(4-1+1)+1;
        int hiHatFrequency2 = r.nextInt(4-1+1)+1;
        int snareFrequency = r.nextInt(16-8+1)+8;
        int kickFrequency = r.nextInt(16-4+1)+4;
        int miscFrequency = r.nextInt(32-16+1)+16;

        for (int i=0; i<songLength; i++){
            if(i%hiHatFrequency==0){
                drum.addNote(i, hiHatPitch, 1);
            }
        }
        for (int i=0; i<songLength; i++){
            if(i%hiHatFrequency2==0){
                drum.addNote(i, hiHatPitch, 1);
            }
        }
        for (int i=0; i<songLength; i++){
            if(i%snareFrequency==0){
                drum.addNote(i, snarePitch, 1);
            }
        }
        for (int i=0; i<songLength; i++){
            if(i%kickFrequency==0){
                drum.addNote(i, kickPitch, 1);
            }
        }
        for (int i=0; i<songLength; i++){
            if(i%miscFrequency==0){
                drum.addNote(i, misc, 1);
            }
        }

        return toString();
    }

    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}