import java.util.ArrayList;
import java.util.Random;

public class BasicAgent implements AgentIF {

    @Override
    public String make_music(int number_of_bars){
//        //get one instrument from each type, store in a list
//        ArrayList<Instrument> instruments = getInstruments();
//
//        String Header = makeHeader(instruments);
//
//        int min = 4;
//        int max = 32
//        for (int i = 0; i< number_of_bars*2; i++){
//            //get random number for length of chord
//            int min = 8
//            Random r = new Random();
//            r.nextInt((max - min) + 1) + min;
//            ArrayList<Note> chord = getChord();



        //Algorithm:
            //get one instrument from each type, store in a list (use getInstruments() )
            //make header using makeHeader

            //Start making chords
                //for loop:
                    //get random note (A-G, # or not)
                    //get random root octave number (2-7)
                    //get random inversion number (0 for root, 1 for 1st, and 2 for second)
                    //call getChord
                        //returns a list of notes
                    //pick random length for chord
                    //make string for all notes
                    //add to header string

            //^^^do we wnna pick one instrument for the chords or let it pick multiple?
            //^^^do we want chords to be able to overlap? do we want gaps?
                //We could randomize starting position based on last chords ending position(start position + length)
                    //Make it a range of end position - something to end position + something

            //make melody
                //for loop:
                    //



          //Questions:
                //Do we want to keep track of what notes (not whole object, just letter and octave?
                    //We could use chord notes to help with the melody?
                //Find a way to make sure melody doesn't jump up and down like crazy?
                    //Use some sort of interval? Base next note based on last note
                        //some melodies have large jumps from time to time(Lady Gaga in Shallow)
                        //how do we control how often the melody jumps?

        }




    }



    public ArrayList<Instrument> getInstruments() {
        //this will eventually pick randomly from the instruments of each type
        //where should the instrument IDs be stored?
        return [new Instrument(8), new Instrument(2), new Instrument(1), new Instrument(13), new Instrument(10)];
    }

    public String makeHeader(ArrayList<Instruments> instruments){
        String header = "{\"instruments\":{";
        String notes = "";
        for(int i = 0; i < instrumentIDs.length; i++){
            header += "\"" + instruments[i], "\":{\"volume\": ", instruments[i].volume, ",\"delay\":", instruments[i].delay, ",\"reverb\":", instruments[i].reverb"}";
            if(i < instrumentIDs.length-1){
                header += ",";
            }
            notes += generate_notes(number_of_bars, instrumentIDs[i], 0);
        }
        header += "}}|110|";
    }


}