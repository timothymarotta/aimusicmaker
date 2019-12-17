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
        drum = new Instrument(2, 2, false, false);
        makeMusic(numOfBars);
        instruments.add(drum);
    }

    /**
     * Edits frequencies of drums and adds them to the drum instrument in the instrument list
     * @param number_of_bars is the number of measures in the song Ex: 8
     * @returns A string in the format that the environment needs (string consists of instruments and notes in the drummer agent). The returned string will be written in a file once returned.
     */
    @Override
    public String makeMusic(int number_of_bars) {
        int songLength = number_of_bars * 16;
        String hiHatPitch = Resources.getHiHatPitch();
        String snarePitch = Resources.getSnarePitch();
        String kickPitch = Resources.getKickPitch();
        String misc = Resources.getMiscellaneousPitch();

        //the following ensures that the hihat frequencies are not the same, and one is odd and one is even

        //both the same
        if(hiHatFrequency - hiHatFrequency2 == 0 || hiHatFrequency2 - hiHatFrequency == 0){
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

        //makes kick divisible by four
        if(kickFrequency%4!=0) {
            kickFrequency += 4 - kickFrequency % 4;
        }

        //makes snare freq closer to kick freq
        snareFrequency = r.nextInt(kickFrequency-1+1)+1;

        snareFrequency += snareFrequency + kickFrequency % snareFrequency;

        //checks snare freq is even
        if (snareFrequency % 2 != 0){
            snareFrequency += 1;
        }

        //double checks snare is less than kick freq, corrects if needed
        while(snareFrequency >= kickFrequency){
            snareFrequency -= 2;
        }


        //if tempo is fast, double all freq to make the drum less frantic sounding
        if(bpm>125){
            hiHatFrequency *= 2;
            hiHatFrequency2 *= 2;
            hiHatFrequency2 += 1;
            snareFrequency *= 2;
            kickFrequency *= 2;
            miscFrequency *= 2;
        }

        //adds notes to drum instrument
        for (int i=0; i<songLength; i++){
            if(i%hiHatFrequency==0){
                drum.addNote(i, hiHatPitch, 1);
            }
            if(i%hiHatFrequency2==0){
                drum.addNote(i, hiHatPitch, 1);
            }
            //endures the snare is not added if the kick is added in that same spot.
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

        return toString();
    }

    /**
     * Passes list of instruments and bpm to the resources's toString
     * @returns A string in the format that the environment can read
     */
    public String toString(){
        return Resources.toString(instruments, bpm);
    }

    /**
     * creates a list of the frequencies of all drum components
     * @returns A list of the frequencies of all drum components
     */
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