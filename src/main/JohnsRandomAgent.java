import java.util.ArrayList;

public class JohnsRandomAgent implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments;

    public JohnsRandomAgent(){
        bpm = 100;
        instruments = new ArrayList<>();
        Instrument myInstrument = new Instrument();
        instruments.add(myInstrument);
        myInstrument.addNote(2, "A6", 2);
        myInstrument.addNote(4, "C6", 2);
    }


    @Override
    public String make_music(int number_of_bars) {
        assert instruments.size() > 0;
        String toReturn = "{\"instruments\":{";

        //Instrument info
        for(int i=0; i<instruments.size(); i++){
            Instrument currInstrument = instruments.get(i);
            toReturn = toReturn + "\"" + currInstrument.instrumentId + "\":{\"volume\":" + currInstrument.volume + ",\"delay\":" + currInstrument.delay + ",\"reverb\":" + currInstrument.reverb + "}}";
        }

        toReturn = toReturn + "}|" + bpm + "|";

        //Note Info
        for(int i=0; i<instruments.size(); i++){
            ArrayList<Note> currNotes = instruments.get(i).notes;
            for(int j=0; j<currNotes.size(); j++){
                toReturn = toReturn + currNotes.get(j).startPosition + " " + currNotes.get(j).note + " " + currNotes.get(j).length + " " + currNotes.get(j).instrumentId + ";";
            }

        }

        return toReturn;
    }
}
