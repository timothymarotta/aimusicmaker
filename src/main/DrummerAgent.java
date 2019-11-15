import java.util.Random;

public class DrummerAgent implements AgentIF {

    int bmp;
    Instrument drum;
// electric -31 / standard- 2
    public DrummerAgent(int bpm){
        this.bmp = bpm;
        drum = new Instrument(2);
        makeMusic(4);
    }

    @Override
    public String makeMusic(int number_of_bars) {
        String hiHatPitch = resourseFileThatKerryWillMake.getHiHatPitch();
        String snarePitch = resourseFileThatKerryWillMake.getSnarePitch();
        String kickPitch = resourseFileThatKerryWillMake.getSnarePitch();

        Random r = new Random();
        int hiHatFrequency = r.nextInt(4-1+1)+1;
        for (int i=0; i<32; i++){ //32 is the length of 2 bars
            drum.addNote(i, hiHatPitch, 1);
        }

        return null;
    }
}