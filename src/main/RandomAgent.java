import java.util.ArrayList;
import java.util.Random;

public class RandomAgent implements AgentIF {
    ArrayList<Instrument> instruments = new ArrayList<Instrument>();
    int bpm = getRandBpm();

    private int getRandBpm(){
        Random rand_bpm = new Random();
        return rand_bpm.nextInt(130-90+1)+90;
    }

    //use a different data structure (linked list?) that stores the number of bars and start pos for each instrument
    public String makeMusic(int number_of_bars){

        //random number of instruments
        Random r = new Random();
        int rand = r.nextInt(6-3+1)+3;

        for(int i = 0; i < rand; i++){
            //gets random instumentID, makes instrument, and adds it to the ArrayList
            int instrumentId = Resources.getRandomInstrumentID();
            Instrument instrument = new Instrument(instrumentId);
            instruments.add(instrument);
        }

        //generate notes for each instrument
        for(int i = 0; i < instruments.size(); i++){
            generateNotes(number_of_bars, i);
        }

        return toString();
    }

    public String toString(){
        return Resources.toString(instruments, bpm);
    }

    private void generateNotes(int number_of_bars, int instrumentInd){
        //for loop gets a
        for(int i = 0; i < number_of_bars*8; i++){
            //random varience for start pos offset
            Random r = new Random();
            int rand = r.nextInt((number_of_bars*16)-1+1)+1; //0 is min so I omitted the adding and removing of 0

            //add note to instrument
            addRandomNote(instrumentInd, number_of_bars, rand);
        }
    }

    //consider a library class to store stuff like all of the available notes
    //3 A6 9 0; = StartingPos Note Length InstrumentID;
    private void addRandomNote(int instrumentInd, int num_of_bars, int startPosition) {
        //list of pitches and octaves
        String[] pitches = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        String[] octaves = {"2", "3", "4", "5", "6", "7"};

        //piece together pitch
        Random rPitch = new Random();
        int pitchesInd = rPitch.nextInt(pitches.length);
        int octavesInd = rPitch.nextInt(octaves.length);
        String pitch = pitches[pitchesInd] + octaves[octavesInd];


        Random rand = new Random();
        int duration = rand.nextInt(8 - 1 + 1) + 1;

        //make sure the song doesn't go overtime
        while ((startPosition + duration > (num_of_bars * 16)) && (duration>0)) {
            duration -= 1;
        }
        if (duration > 0) {

            //make new note and add it
            Note note = new Note(startPosition, pitch, duration, instruments.get(instrumentInd).instrumentId);
            instruments.get(instrumentInd).addNote(note);

        }
    }

}
