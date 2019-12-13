import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChordAgentV2 implements AgentIF {

    int bpm;
    ArrayList<Instrument> instruments = new ArrayList<>();

    ArrayList<Integer> drumFrequencies;
    String key;

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
    @Override
    public String makeMusic(int number_of_bars) throws Exception{
        Resources resources = new Resources();

        instruments.add(new Instrument(0));


        //2. Create a list of numerals that will represent an abstract chord progression
        //   number of chords should be equal to number_of_bars
        //  *Ideas for intelligence:
        //    - Create a search space with all possible chord progression possibilities
        //    - Gather data about popular chord progressions to use as a heuristic
        //   For now, it will be hardcoded
        List<String> numerals  = resources.getRandomHardcodedChordProgression();

        //3. turn numerals into a list of lists of pitches
        ArrayList<ChordInfo> chordInfoProgression = new ArrayList();
        for(int i=0; i<numerals.size(); i++){
            ChordInfo newChord = Resources.getChordInfoFromKeyAndNumeral(key, numerals.get(i));
            chordInfoProgression.add(newChord);
        }




        //4. Create Note objects for the instrument based on the pitches from chordProgression
        //   This is where the rhythm/grove is "creatively" decided.
        //   To keep this agent simple, the first chord will be played in the first bar, second chord in the second bar, etc...
        //  *This is an opportunity for intelligently listening to the drummerAgent*

        //i is current starting position
        int i = 0;
        //current ending position
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
            ArrayList<String> currChordNotes = resources.getChord(chordInfoProgression.get(chordInfoIndex).root, 4, chordInfoProgression.get(chordInfoIndex).inversion, chordInfoProgression.get(chordInfoIndex).chordType, "");
            if(getHiHat1){
                int chance = r.nextInt(7);
                if (i % 2 != 0) {
                    int startDelay = r.nextInt(4);
                    i += 1;
                }
                if(drumFrequencies.get(0)<10 && drumFrequencies.get(1)<10) {
                    chance = r.nextInt(7);
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

                iEnd = findNextHiHat(i, drumFrequencies.get(0));
                chance = r.nextInt(3);
                while(chance==2 && iEnd-i<12){
                    iEnd = findNextHiHat(iEnd, drumFrequencies.get(0));
                    chance = r.nextInt(5);
                }
            }
            else{

                iEnd = findNextHiHat(i, drumFrequencies.get(1));
                int chance = r.nextInt(4);

                //delay start
                while(chance==2){
                    iEnd = findNextHiHat(iEnd, drumFrequencies.get(1));
                    chance = r.nextInt(5);
                }

                //make chords start on downbeat
                if(i%2!=0){
                    i+=1;
                }
            }

            //swap beginning and end if they happened to get mixed up
            if(iEnd<i){
                int temp = i;
                i = iEnd+1;
                iEnd = temp;
            }

            //don't let ending positions past the number of bars
            if(iEnd>number_of_bars*16){
                iEnd = number_of_bars*16;
            }

            //arpeggios:
            //must be length of 6 or greater
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
                    Note currNote = new Note(i, currChordNotes.get(j), iEnd-i, instruments.get(0).getInstrumentId());
                    //swap beginning and end if they happened to get mixed up
                    if(iEnd<i){
                        int temp = i;
                        i = iEnd+1;
                        iEnd = temp;
                    }
                    instruments.get(0).addNote(currNote);
                }
            }
            else {
                for (int j = 0; j < currChordNotes.size(); j++) {
                    //swap beginning and end if they happened to get mixed up
                    if(iEnd<i){
                        int temp = i;
                        i = iEnd+1;
                        iEnd = temp;
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
//        for (int i = 0; i < chordInfoProgression.size(); i++) {
//            ArrayList<String> currChordNotes = resources.getChord(chordInfoProgression.get(i).root, 4, chordInfoProgression.get(i).inversion, chordInfoProgression.get(i).chordType, "");
//            for (int j = 0; j < currChordNotes.size(); j++) {
//                for (int k = i; k < number_of_bars; k+=4) {
//                    Note currNote = new Note(k * 16, currChordNotes.get(j), 16, instruments.get(0).getInstrumentId());
//                    instruments.get(0).addNote(currNote);
//                }
//            }
//        }
        //TODO: make chord listen to the drummer agent
        return toString();
    }

    //given the min starting pos, the program will return the next starting position of a kick
    public int findNextKick(int min){
        int pos = 0;
        while (pos<=min){
            pos += drumFrequencies.get(3);
        }
        return pos;
    }

    public int findNextSnare(int min){
        int pos = 0;
        while (pos<=min){
            pos += drumFrequencies.get(2);
        }
        return pos;
    }
    //given the min starting pos, the program will return the next starting position of a hiHat
    //frequecy is passed into this one and not kick because this will specify which hiHat (since there are two frequencies)
    public int findNextHiHat(int min, int freq){
        int pos = 0;
        while (pos<min){
            pos += freq;
        }
        return pos;
    }

    public String toString(){
        return Resources.toString(instruments, bpm);
    }
}
