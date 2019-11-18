import java.util.ArrayList;

public class Chord {

    ArrayList<String> pitches;

    public Chord(String pitchLetter, int octave, String chordType){
        //create AL of pitches based on parameters
        pitches.add(pitchLetter + octave);
        //Hey Tim


    }

    public ArrayList<String> getChord() {
        return pitches;
    }
}
