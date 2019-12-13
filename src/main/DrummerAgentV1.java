import java.util.ArrayList;
import java.util.Random;

public class DrummerAgentV1 implements AgentIF {

    int bpm;
    Instrument drum;
    ArrayList<Instrument> instruments = new ArrayList<>();

    Random r = new Random();
    int hiHatFrequency = r.nextInt(6-2+1)+2;
    int hiHatFrequency2 = r.nextInt(9-5+1)+1;
    int snareFrequency = r.nextInt(8-4+1)+4;
    int kickFrequency = r.nextInt(16-2+1)+2;
    int miscFrequency = r.nextInt(48-16+1)+16;



// electric -31 / standard- 2
    public DrummerAgentV1(int bpm, int numOfBars){
        this.bpm = bpm;
        drum = new Instrument(2);
        makeMusic(numOfBars);
        instruments.add(drum);
    }

    @Override
    public String makeMusic(int number_of_bars) {
        int songLength = number_of_bars * 16;
        String hiHatPitch = Resources.getHiHatPitch();
        String snarePitch = Resources.getSnarePitch();
        String kickPitch = Resources.getKickPitch();
        String misc = Resources.getMiscellaneousPitch();

        if(hiHatFrequency % hiHatFrequency2 == 0 || hiHatFrequency2 % hiHatFrequency == 0){
            hiHatFrequency2 += 1;
        }
        //both odd
        if(hiHatFrequency % 2 != 0 && hiHatFrequency2 %2 !=0){
            hiHatFrequency2 += 1;
            kickFrequency = hiHatFrequency2;
        }
        //both even
        else if(hiHatFrequency % 2 != 1 && hiHatFrequency2 %2 != 1){
            hiHatFrequency2 += 1;
            kickFrequency = hiHatFrequency;

        }

        if(kickFrequency%4!=0) {
            kickFrequency += 4 - kickFrequency % 4;
        }

        snareFrequency = r.nextInt(kickFrequency-1+1)+1;

        snareFrequency += snareFrequency + kickFrequency % snareFrequency;

        if (snareFrequency % 2 != 0){
            snareFrequency += 1;
        }


        while(snareFrequency >= kickFrequency){
            snareFrequency -= 2;
        }


        if(bpm>125){
            hiHatFrequency *= 2;
            hiHatFrequency2 *= 2;
            hiHatFrequency2 += 1;
            snareFrequency *= 2;
            kickFrequency *= 2;
            miscFrequency *= 2;
        }


        for (int i=0; i<songLength; i++){
            if(i%hiHatFrequency==0){
                drum.addNote(i, hiHatPitch, 1);
            }
            if(i%hiHatFrequency2==0){
                drum.addNote(i, hiHatPitch, 1);
            }
//            if(i%snareFrequency==0){
//                drum.addNote(i, snarePitch, 1);
//            }
            if(i%kickFrequency==0){
                drum.addNote(i, kickPitch, 1);
            }
            else {
                if(i%snareFrequency==0){
                    drum.addNote(i, snarePitch, 1);
                }
            }
            if(i%miscFrequency==0){
                drum.addNote(i, misc, 1);
            }
        }

        ArrayList<Integer> freq = getFrequencies();
        for(int i = 0; i < freq.size(); i++){
            System.out.println(freq.get(i));
        }

        return toString();
    }

    public String toString(){
        return Resources.toString(instruments, bpm);
    }

    public ArrayList<Integer> getFrequencies(){
        ArrayList<Integer> frequencies = new ArrayList<Integer>();
        frequencies.add(hiHatFrequency);
        frequencies.add(hiHatFrequency2);
        frequencies.add(snareFrequency);
        frequencies.add(kickFrequency);
        frequencies.add(miscFrequency);

        return frequencies;
    }
}