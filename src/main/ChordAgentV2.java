import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChordAgentV2 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments = new ArrayList<>();

    ArrayList<Integer> drumFrequencies;
    String key;

    /**
     * Constructor for ChordV2 Agent
     * drumFrequenciesIn- a list of the frequencies at which different parts of the drums are in the song
     * keyIn- the key that the music will be in
     * bpm- the beats per minute, or the tempo
     */
    public ChordAgentV2(ArrayList<Integer> drumFrequenciesIn, String keyIn, int bpmIn){
        drumFrequencies = drumFrequenciesIn;
        Resources resources = new Resources();
        if (keyIn.equals("r")){
            key = resources.getRandomKey();
        }
        else{
            key = keyIn;
        }
        bpm = bpmIn;
    }

    /**
     * Picks a chord progression and places chords differently depending on the drum frequencies
     * @param number_of_bars is the number of measures in the song Ex: 8
     * @returns A string in the format that the environment needs. The returned string will be written in a file once returned.
     */
    @Override
    public String makeMusic(int number_of_bars) throws Exception{
        Resources resources = new Resources();

        instruments.add(new Instrument(0));


        //2. Create a list of numerals that will represent an abstract chord progression
        //number_of_bars should be divisible by number of chords in progression
        List<String> numerals  = resources.getRandomHardcodedChordProgression();

        //3. turn numerals into a list of lists of chordInfo objects
        ArrayList<ChordInfo> chordInfoProgression = new ArrayList();
        for(int i=0; i<numerals.size(); i++){
            ChordInfo newChord = Resources.getChordInfoFromKeyAndNumeral(key, numerals.get(i));
            chordInfoProgression.add(newChord);
        }


        //i is current starting position for chords
        int i = 0;
        //current ending position for chords
        int iEnd;

        Random r = new Random();
        //indicates which chordInfo we're on
        int chordInfoIndex = 0;

        //boolean that changes sometimes.
        // true makes the agent listen to hihat1,
        // false makes the agent listen to hihat2
        boolean getHiHat1 = true;

        //while we aren't at the end of the track
        while (i<number_of_bars*16){

            //string list of the pitches in our current chord
            ArrayList<String> currChordNotes = resources.getChord(chordInfoProgression.get(chordInfoIndex).root, 4, chordInfoProgression.get(chordInfoIndex).inversion, chordInfoProgression.get(chordInfoIndex).chordType, "");

            //listening to hiHet1
            if(getHiHat1){

                //updates start position, adds possibility for delay before next chord
                int chance = r.nextInt(7);
                if (i % 2 != 0) {
                    int startDelay = r.nextInt(4);
                    i += startDelay;
                }
                if(drumFrequencies.get(0)<10 && drumFrequencies.get(1)<10) {
                    if (chance == 2) {
                        int startDelay = r.nextInt(6);
                        i += startDelay;
                    }
                }
                else{
                    chance = r.nextInt(9);
                    if (chance == 2) {
                        int startDelay = r.nextInt(5);
                        i += startDelay;
                    }
                }

                i = findNextHiHat(i, drumFrequencies.get(0));
                iEnd = findNextHiHat(i, drumFrequencies.get(0));
                chance = r.nextInt(3);

                //possibility to make notes longer
                while(chance==2 && iEnd-i<12){
                    iEnd = findNextHiHat(iEnd, drumFrequencies.get(0));
                    chance = r.nextInt(5);
                }
            }

            //listening to hiHat2
            else{

                //updates start position, adds possibility for delay before next chord
                int chance = r.nextInt(5);
                if (i % 2 != 0) {
                    int startDelay = r.nextInt(3);
                    i += startDelay;
                }
                if(drumFrequencies.get(0)<10 && drumFrequencies.get(1)<10) {
                    if (chance == 4) {
                        int startDelay = r.nextInt(6);
                        i += startDelay;
                    }
                }
                else{
                    chance = r.nextInt(9);
                    if (chance == 1) {
                        int startDelay = r.nextInt(5);
                        i += startDelay;
                    }
                }

                i = findNextHiHat(i, drumFrequencies.get(1));
                iEnd = findNextHiHat(i, drumFrequencies.get(1));
                chance = r.nextInt(3);

                //possibility to make notes longer
                while(chance==2 && iEnd-i<12){
                    iEnd = findNextHiHat(iEnd, drumFrequencies.get(1));
                    chance = r.nextInt(5);
                }
            }

            //don't let ending positions past the number of bars
            if(iEnd>number_of_bars*16){
                iEnd = number_of_bars*16;
            }

            //arpeggios:
            //must be length of 2 or greater, 1/4 chance of arpeggio
            int arp = r.nextInt(4);
            if(iEnd-i>=2 && arp == 3){

                //moves end point of end of chord up.
                //-1 because the first note's starting position won't change
                for (int w = 0; w < currChordNotes.size()-1; w++){
                    iEnd = findNextHiHat(iEnd,  drumFrequencies.get(r.nextInt(2)));
                }

                //each note of the chord will start at the same time as a different hihat
                for (int j = 0; j < currChordNotes.size(); j++) {
                    //the first note will not move their starting position
                    if (j>0){
                        for (int h = 0; h <j; h++){
                            i = findNextHiHat(i, drumFrequencies.get(r.nextInt(2)));
                        }
                    }
                    //place arpeggio notes
                    if(iEnd<i){
                        int temp = i;
                        i = iEnd;
                        iEnd = temp;
                    }

                    if (iEnd == i){
                        iEnd+=r.nextInt(3)+1;
                    }
                    Note currNote = new Note(i, currChordNotes.get(j), iEnd-i, instruments.get(0).getInstrumentId());
                    //swap beginning and end if they happened to get mixed up
                    instruments.get(0).addNote(currNote);
                }
            }
            else {
                for (int j = 0; j < currChordNotes.size(); j++) {
                    //swap beginning and end if they happened to get mixed up
                    if(iEnd<i){
                        int temp = i;
                        i = iEnd;
                        iEnd = temp;
                    }
                    if (iEnd == i){
                        iEnd+=r.nextInt(3)+1;
                    }
                    Note currNote = new Note(i, currChordNotes.get(j), iEnd - i, instruments.get(0).getInstrumentId());
                    instruments.get(0).addNote(currNote);
                }
            }

            //get earliest starting position for next chord
            i = iEnd+1;

            //goes to next chord
            chordInfoIndex += 1;
            if (chordInfoIndex>=chordInfoProgression.size()){
                chordInfoIndex = 0;
            }

            //possibility of looking at other highhat frequency
            int chance = r.nextInt(3);
            if(chance==2){
                if(getHiHat1){
                    getHiHat1 = false;
                }
                else{
                    getHiHat1 = true;
                }
            }

        }
        return toString();
    }

    /**
     * Based on where you are currently in the song, it will find the next time the kick in the drum is present in the song
     * @param min is the place you currently are on in the song
     * @returns An int of the starting position of the next kick
     */
    //given the min starting pos, the program will return the next starting position of a kick
    public int findNextKick(int min){
        int pos = 0;
        while (pos<=min){
            pos += drumFrequencies.get(3);
        }
        return pos;
    }

    /**
     * Based on where you are currently in the song, it will find the next time the snare in the drum is present in the song
     * @param min is the place you currently are on in the song
     * @returns An int of the starting position of the next snare
     */
    public int findNextSnare(int min){
        int pos = 0;
        while (pos<=min){
            pos += drumFrequencies.get(2);
        }
        return pos;
    }

    //given the min starting pos, the program will return the next starting position of a hiHat
    //frequecy is passed into this one and not kick because this will specify which hiHat (since there are two frequencies)
    /**
     * Based on where you are currently in the song, it will find the next time the hiHat in the drum is present in the song
     * @param min is the place you currently are on in the song
     * @param freq is the the frequency of which hiHat you want (there are two hiHats
     * @returns An int of the starting position of the next desired hiHat
     */
    public int findNextHiHat(int min, int freq){
        int pos = 0;
        while (pos<min){
            pos += freq;
        }
        return pos;
    }

    /**
     * Passes list of instruments and bpm to the resources's toString
     * @returns A string in the format that the environment can read
     */
    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
