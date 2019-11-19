import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Scale {
    private HashMap<String, List<String>> scales = new HashMap<>();

    private void initializeScales(){
        scales.put("C", new ArrayList<>(Arrays.asList("C", "D", "E", "F", "G", "A", "B")));
        scales.put("C#", new ArrayList<>(Arrays.asList("C#", "D#", "F", "F#", "G#", "A#", "C")));
        scales.put("D", new ArrayList<>(Arrays.asList("D", "E", "F#", "G", "A", "B", "C#")));
        scales.put("D#", new ArrayList<>(Arrays.asList("D#", "F", "G", "G#", "A#", "B#", "D")));
        scales.put("E", new ArrayList<>(Arrays.asList("E, F#, G#, A, B, C#, D#, E".split(", "))));
        scales.put("F", new ArrayList<>(Arrays.asList("F, G, A, A#, C, D, E, F".split(", "))));
        scales.put("F#", new ArrayList<>(Arrays.asList("F#, G#, A#, B, C#, D#, E#".split(", "))));
        scales.put("G", new ArrayList<>(Arrays.asList("G, A, B, C, D, E, F#".split(", "))));
        scales.put("G#", new ArrayList<>(Arrays.asList("G#, A#, B#, C#, D#, E#, G".split(", "))));
        scales.put("A", new ArrayList<>(Arrays.asList("A, B, C#, D, E, F#, G#".split(", "))));
        scales.put("A#", new ArrayList<>(Arrays.asList("A#, B#, D, D#, E#, G, A".split(", "))));
        scales.put("B", new ArrayList<>(Arrays.asList("B, C#, D#, E, F#, G#, A#".split(", "))));
    }

    public Scale(){
        initializeScales();
    }
    public List<String> getScale(String scaleName, String scaleMode){
        return scales.get(scaleName);
    }
}
