public class ConductorAgent implements AgentIF {



    @Override
    public String makeMusic(int number_of_bars) throws Exception {

        int bpm = 160;

        DrummerAgentV1 drummer = new DrummerAgentV1(bpm, number_of_bars);



        return null;
    }
}
