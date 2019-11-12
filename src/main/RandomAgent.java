import java.util.Random;

public class RandomAgent implements AgentIF {
    //use a different data structure (linked list?) that stores the number of bars and start pos for each instrument
    public String make_music(int number_of_bars, int[] instrumentIDs){
        String header = "{\"instruments\":{";
        String notes = "";
        for(int i = 0; i < instrumentIDs.length; i++){
            header += "\"" + instrumentIDs[i] + "\":{\"volume\":1,\"delay\":false,\"reverb\":false}";
            if(i < instrumentIDs.length-1){
                header += ",";
            }
            notes += generate_notes(number_of_bars, instrumentIDs[i], 0);
        }
        header += "}}|110|";
        return header+notes;
    }

    private String generate_notes(int number_of_bars, int instrumentID, int start_pos){
        String notes = "";
        for(int i = start_pos; i < number_of_bars*2; i+=2){
            notes += random_note(instrumentID, i);
        }
        return notes;
    }
    //consider using pass-by reference to keep track of length of bars

    //consider a library class to store stuff like all of the available notes
    //3 A6 9 0; = StartingPos Note Length InstrumentID;
    private String random_note(int instrumentID, int starting_note){
        String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        String[] octaves = {"2", "3", "4", "5", "6", "7"};
        Random rand = new Random();
        //starting position
        String note = Integer.toString(starting_note);
        //note
        note += " " + notes[rand.nextInt(notes.length)];
        //octave
        note += octaves[rand.nextInt(octaves.length)];
        //length
        note += " " + (rand.nextInt(4) + 1);
        //instrument ID
        note += " " + instrumentID + ";";
        return note;
    }

}
