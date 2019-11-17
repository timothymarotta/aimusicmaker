import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            if(pitch1 == pitch2){
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
            String pitch2 = Resources.getHiHatPitch();
            if(pitch1 == pitch2){
                equal = true;
            }
            i++;
        }

        assertTrue(i<11);

    }


}
