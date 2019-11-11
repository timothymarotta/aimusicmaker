import java.util.ArrayList;

public class Instrument {
    int instrumentId;
    int volume;
    boolean delay;
    boolean reverb;
    ArrayList<Note> notes;

    private void setDefaultValues(){
        this.instrumentId = 0;
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

    public Instrument(){
        setDefaultValues();
    }

}
