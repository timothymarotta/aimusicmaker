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
//        String hiHatPitch = resourseFileThatKerryWillMake.getHiHatPitch();
//        String snarePitch = resourseFileThatKerryWillMake.getSnarePitch();
//        String kickPitch = resourseFileThatKerryWillMake.getSnarePitch();
        String hiHatPitch = "F#3";
        String snarePitch = "D3";
        String kickPitch = "C3";
        String crashPitch = "C#4";

        Random r = new Random();
        int hiHatFrequency = r.nextInt(4-1+1)+1;
        int hiHatFrequency2 = r.nextInt(4-1+1)+1;
        int snareFrequency = r.nextInt(16-8+1)+1;
        int kickFrequency = r.nextInt(16-4+1)+1;
        int crashFrequency = r.nextInt(32-16+1)+1;

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
            if(i%crashFrequency==0){
                drum.addNote(i, crashPitch, 1);
            }
        }

        return toString();
    }

    public String toString() {
        assert instruments.size() > 0;
        String toReturn = "{\"instruments\":{";

        //Instrument info
        for (int i = 0; i < instruments.size(); i++) {
            Instrument currInstrument = instruments.get(i);
            toReturn = toReturn + "\"" + currInstrument.instrumentId + "\":{\"volume\":" + currInstrument.volume + ",\"delay\":" + currInstrument.delay + ",\"reverb\":" + currInstrument.reverb + "}";
            if (i < instruments.size() - 1) {
                toReturn += ",";
            }
        }
        toReturn = toReturn + "}}|" + bpm + "|";

        //Note Info
        for (int i = 0; i < instruments.size(); i++) {
            ArrayList<Note> currNotes = instruments.get(i).notes;
            for (int j = 0; j < currNotes.size(); j++) {
                toReturn = toReturn + currNotes.get(j).startPosition + " " + currNotes.get(j).pitch + " " + currNotes.get(j).duration + " " + currNotes.get(j).instrumentId + ";";
            }
        }
        return toReturn;
    }
}