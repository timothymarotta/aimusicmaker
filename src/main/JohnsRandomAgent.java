import java.util.ArrayList;

public class JohnsRandomAgent implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments;

    public JohnsRandomAgent(){
        bpm = 100;
        instruments = new ArrayList<>();
        Instrument myInstrument = new Instrument();

    }


    @Override
    public String make_music(int number_of_bars) {
        return null;
    }
}
