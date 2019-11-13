import java.util.ArrayList;

public class Instrument {
    int instrumentId;
    int volume;
    boolean delay;
    boolean reverb;
    ArrayList<Note> notes;

    public Instrument(int instrumentId){
        setDefaultValues(instrumentId);
    }
    public Instrument(int instrumentId, int volume, boolean delay, boolean reverb){
        this.instrumentId = instrumentId;
        this.volume = volume;
        this.delay = delay;
        this.reverb = reverb;
        this.notes = new ArrayList<>();
    }

    private void setDefaultValues(int instrumentId){
        this.instrumentId = instrumentId;
        this.volume = 1;
        this.delay = false;
        this.reverb = false;
        this.notes = new ArrayList<>();
    }

    private void setRandomValues(){
        //TODO
    }

    public void addNote(int startPosition, String note, int length){
        notes.add(new Note(startPosition, note, length, instrumentId));
    }

    public void addNote(Note noteIn){
        notes.add(noteIn);
    }



}
