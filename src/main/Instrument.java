import java.util.ArrayList;

public class Instrument {
    int instrumentId;
    int volume;
    boolean delay;
    boolean reverb;
    ArrayList<Note> notes;

    private void setDefaultValues(){
        this.instrumentId = 1;
        this.volume = 1;
        this.delay = false;
        this.reverb = false;
        this.notes = new ArrayList<>();
        addDefaultNotes();
    }

    private void setRandomValues(){
        //TODO
    }

    private void addDefaultNotes(){
        notes.add(new Note(2, "A6", 2, this.instrumentId));
        notes.add(new Note(4, "C6", 2, this.instrumentId));
    }

    public Instrument(){
        setDefaultValues();
    }

}
