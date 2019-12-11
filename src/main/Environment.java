import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
// Written by Timothy Marotta
// Date: November 11, 2019

public class Environment {
    private static void writeToFile(String musicData, String filename) throws IOException {
        FileWriter out = new FileWriter(filename);
        out.write(musicData);
        out.close();
    }
    public static void main(String[] args) throws IOException, Exception {
        //create new scanner and do any initialization tasks as needed
        System.out.println("Initializing...");
        Scanner in = new Scanner(System.in);

        //ask for filename to write to
        //catch input as String. Note all text files are stored in project root
        System.out.println("Enter name of file:  ");
        String filename = in.nextLine();

        // ask for number of bars
        //catch input as Integer and multiply by 16
        System.out.println("Enter number of bars: ");
        String numberOfBarsStr = in.nextLine();
        int numberOfBars = Integer.valueOf(numberOfBarsStr);

        //check to make sure it's divisible by 4
        while (numberOfBars%4 != 0){
            System.out.println("Number of bars must be divisible by 4.");
            System.out.println("Please enter number of bars: ");
            numberOfBarsStr = in.nextLine();
            numberOfBars = Integer.valueOf(numberOfBarsStr);
        }

        //ask for tempo
        int tempo = -5;
        boolean validTempo = false;
        boolean validNum = true;
        Random rand = new Random();

        System.out.println("Enter tempo- you can choose 'slow,' 'medium', 'fast', 'random' or enter the BPM: ");
        String tempoStr = in.nextLine();

        while(!validTempo) {
            //These values are subject to change!
            if(!tempoStr.equals("slow") && !tempoStr.equals("medium") && !tempoStr.equals("fast") && !tempoStr.equals("random")) {
                try {
                    Integer.parseInt(tempoStr);
                } catch (NumberFormatException e) {
                    validNum = false;
                }
            }

            if (tempoStr.equals("slow")) {
                validTempo = true;
                tempo = rand.nextInt(70-50)+50;

            } else if (tempoStr.equals("medium")) {
                validTempo = true;
                tempo = rand.nextInt(120-70)+70;

            } else if (tempoStr.equals("fast")) {
                validTempo = true;
                tempo = rand.nextInt(180-120)+120;

            } else if (tempoStr.equals("random") || tempoStr.equals("r")) {
                validTempo = true;
                tempo = rand.nextInt(180-50)+50;
            }

            else if (validNum && Integer.parseInt(tempoStr) > 50 && Integer.parseInt(tempoStr) < 200) {
                    validTempo = true;
                    tempo = Integer.parseInt(tempoStr);
            }

            else {
                validNum = true;
                System.out.println("Please enter a valid input.\nYou can type in 'slow', 'medium', 'fast', 'random', or a number between 40 and 200: ");
                tempoStr = in.nextLine();
            }
        }

        //ask for key
        ArrayList<String> validNotesIn = new ArrayList<>(Arrays.asList("c", "C", "c#", "C#", "d", "D#", "e", "E", "f", "F", "f#", "F#", "g", "G", "g#", "G#", "a", "A", "a#",
        "A#", "b", "B"));
        String key, mmStr;
        boolean validKey = false;

        System.out.println("Enter starting key note or type 'random': ");
        String keyStr = in.nextLine();

        while(!validKey) {
            if (keyStr.equals("random") || keyStr.equals("r")) {
                //the agent's constructor should recognize this as asking for a random key
                key = Integer.toString(-1);
                validKey = true;

            } else if (validNotesIn.contains(keyStr)) {
                //get major or minor
                System.out.println("Select 'major' or 'minor': ");
                mmStr = in.nextLine();

                while (!validKey) {
                    if (mmStr.equals("major")) {
                        validKey = true;
                    }
                    else if (mmStr.equals("minor")){
                        validKey = true;
                    } else {
                        System.out.println("Please select 'major' or 'minor': ");
                        mmStr = in.nextLine();
                    }

                //also possible to keep these two variables separate
                key = keyStr+mmStr;
                }
            }
            else {
                System.out.println("Please enter a valid key, or 'random': ");
                keyStr = in.nextLine();
            }
        }

        //call write to file (will eventually change when we have agents to work with to accommodate multiple agents)
        ConductorAgent myAgent = new ConductorAgent(tempo, keyStr);
        myAgent.makeMusic(numberOfBars);
        writeToFile(myAgent.toString(), filename);
        System.out.println(filename + " was created.");
    }
}

