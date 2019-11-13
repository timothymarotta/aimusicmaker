import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgentV2Test {

    @Test
    public void toStringTest(){
        //One instrument with two notes
        String expected1 = "{\"instruments\":{\"0\":{\"volume\":1,\"delay\":false,\"reverb\":false}}}|100|2 A6 2 0;4 C6 2 0;";
        AgentV2 myAgent = new AgentV2(100);
        Instrument inst1 = new Instrument(0, 1, false, false);
        inst1.addNote(2, "A6", 2);
        inst1.addNote(4, "C6", 2);
        myAgent.addInstrument(inst1);

        String actual1 = myAgent.toString();
        assertEquals(expected1, actual1);

        //Two instruments
        Instrument inst2 = new Instrument(1, 1, false, false);
        inst2.addNote(2, "G4", 2);
        inst2.addNote(4, "C6", 4);
        inst2.addNote(new Note(5, "G4", 3, 1));
        myAgent.addInstrument(inst2);
        String expected2 = "{\"instruments\":{\"0\":{\"volume\":1,\"delay\":false,\"reverb\":false},\"1\":{\"volume\":1,\"delay\":false,\"reverb\":false}}}|100|2 A6 2 0;4 C6 2 0;2 G4 2 1;4 C6 4 1;5 G4 3 1;";
        String actual2 = myAgent.toString();
        assertEquals(expected2, actual2);

        //Four instruments!!!
        Instrument inst3 = new Instrument(2, 1, false, false);
        inst3.addNote(4, "A5", 3);
        Instrument inst4 = new Instrument(3, 1, false, false);
        inst4.addNote(5, "B5", 4);
        myAgent.addInstrument(inst3);
        myAgent.addInstrument(inst4);
        String actual3 = "{\"instruments\":{\"0\":{\"volume\":1,\"delay\":false,\"reverb\":false},\"1\":{\"volume\":1,\"delay\":false,\"reverb\":false},\"2\":{\"volume\":1,\"delay\":false,\"reverb\":false},\"3\":{\"volume\":1,\"delay\":false,\"reverb\":false}}}|100|2 A6 2 0;4 C6 2 0;2 G4 2 1;4 C6 4 1;5 G4 3 1;4 A5 3 2;5 B5 4 3;";
        String expected3 = myAgent.toString();
        assertEquals(expected3, actual3);
    }
}
