import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class resourcesTest {


    @Test
    public void getHiHatPitchTest(){
        String allPitches[] = new String[]{"C#7", "A#6", "E6", "D#6", "C#", "A#5", "A5", "D5", "C5", "B4", "A4", "G4", "F#4", "D#4", "C#4", "A#", "G#3", "F#3", "D#3", "C#3", "G2", "E2"};
        List<String> list = Arrays.asList(allPitches);

        String pitch1 = Resources.getHiHatPitch();
        assertTrue(list.contains(pitch1));

        int equal = 0;
        int i = 0;

        while(i<10){
            String pitch2 = Resources.getHiHatPitch();
            assertTrue(list.contains(pitch2));
            if(pitch1.equals(pitch2)){

                equal +=1;
            }
            i++;
        }

        assertTrue(equal<(i/2));

    }

    @Test
    public void getSnarePitchTest(){
        String allPitches[] = new String[]{"E3", "D3"};
        List<String> list = Arrays.asList(allPitches);

        String pitch1 = Resources.getSnarePitch();
        assertTrue(list.contains(pitch1));

        boolean equal = false;
        int i = 0;

        while(i<10 && equal == false){
            String pitch2 = Resources.getSnarePitch();
            assertTrue(list.contains(pitch2));
            if(pitch1.equals(pitch2)){
                equal = true;
            }
            i++;
        }

        assertTrue(i<11);

    }

    @Test
    public void getKickPitchTest(){
        String allPitches[] = new String[]{"F3", "B2", "C3", "G3", "A3", "B3", "C4", "D4", "E5", "D#7", "D7"};
        List<String> list = Arrays.asList(allPitches);

        String pitch1 = Resources.getKickPitch();
        assertTrue(list.contains(pitch1));

        int equal = 0;
        int i = 0;

        while(i<8){
            String pitch2 = Resources.getKickPitch();
            assertTrue(list.contains(pitch2));
            if(pitch1.equals(pitch2)){
                equal +=1;
            }
            i++;
        }

        assertTrue(equal<(i/2));

    }

    @Test
    public void miscellaneousTest(){
        String allPitches[] = new String[]{"C7", "B6", "A6", "G6", "F#6", "C6", "B5", "A#4", "F4", "D#3", "A#2", "F#2", "F2", "E2"};
        List<String> list = Arrays.asList(allPitches);

        String pitch1 = Resources.getMiscellaniousPitch();
        assertTrue(list.contains(pitch1));

        int equal = 0;
        int i = 0;

        while(i<10){
            String pitch2 = Resources.getMiscellaniousPitch();
            assertTrue(list.contains(pitch2));
            if(pitch1.equals(pitch2)){
                equal +=1;
            }
            i++;
        }

        assertTrue(equal<(i/2));

    }

    @Test
    public void getRandomInstrumentIdTest(){
        Integer allInstruments[] = new Integer[]{0, 8, 17, 25, 26, 2, 31, 19, 34, 21, 1, 4, 5, 29, 32, 35, 38, 22, 33, 2, 6, 7, 13, 14, 15, 16, 9, 10, 11, 12, 18, 20, 23};
        List<Integer> list = Arrays.asList(allInstruments);
        for(int i = 0; i<allInstruments.length*2; i++){
            int randId = Resources.getRandomInstrumentID();
            assertTrue(list.contains(randId));
        }
    }

    @Test
    public void getScaleTest(){

        //Major
        Resources resource = new Resources();
        List<String> scale1 = resource.getScale("C", "Major");
        ArrayList<String> realScale = new ArrayList<>(Arrays.asList("C", "D", "E", "F", "G", "A", "B"));
        assertEquals(realScale, scale1);

        List<String> scale2 = resource.getScale("B", "Major");
        ArrayList<String> realScale2 = new ArrayList<>(Arrays.asList("B, C#, D#, E, F#, G#, A#".split(", ")));
        assertEquals(realScale2, scale2);

        List<String> scale3 = resource.getScale("F#", "Major");
        ArrayList<String> realScale3 = new ArrayList<>(Arrays.asList("F#, G#, A#, B, C#, D#, E#".split(", ")));
        assertEquals(realScale3, scale3);


        //Minor
        List<String> minorScale1 = resource.getScale("C", "Minor");
        ArrayList<String> realMinorScale1 = new ArrayList<>(Arrays.asList("C", "D", "D#", "F", "G", "G#", "A#"));
        assertEquals(realMinorScale1, minorScale1);

        List<String> minorScale2 = resource.getScale("B", "Minor");
        ArrayList<String> realMinorScale2 = new ArrayList<>(Arrays.asList("B, C#, D, E, F#, G, A".split(", ")));
        assertEquals(realMinorScale2, minorScale2);

        List<String> minorScale3 = resource.getScale("F#", "Minor");
        ArrayList<String> realMinorScale3 = new ArrayList<>(Arrays.asList("F#, G#, A, B, C#, D, E".split(", ")));
        assertEquals(realMinorScale3, minorScale3);

        //Diminished


        //Augmented

    }

}
