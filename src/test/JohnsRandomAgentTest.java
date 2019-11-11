import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JohnsRandomAgentTest {

    @Test
    public void getExpectedValuesTest(){
        String expected = "{\"instruments\":{\"0\":{\"volume\":1,\"delay\":false,\"reverb\":false}}}|100|2 A6 2 0;4 C6 2 0;";
        AgentIF johnsAgent = new JohnsRandomAgent();
        String actual = johnsAgent.make_music(1);
        assertEquals(expected, actual);
    }
}
