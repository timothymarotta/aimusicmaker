import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Environment {
    public static void main(String[] args) throws IOException {
        AgentIF randomAgent = new RandomAgent();
        int[] instruments = {4, 29, 31, 3, 9, 10};
        String music = randomAgent.make_music(40, instruments);
        output_file(music, "testFile");
    }

    public static void output_file(String music, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(music);
        printWriter.close();
    }
}

