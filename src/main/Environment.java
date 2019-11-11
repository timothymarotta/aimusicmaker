import java.io.*;
import java.util.Scanner;
// Written by Timothy Marotta
// Date: November 11, 2019

public class Environment {
    private static void writeToFile(Integer numberOfBars, String filename) throws IOException {
        FileWriter out = new FileWriter(filename);
        out.write(numberOfBars);
        out.close();
    }
    public static void main(String[] args) throws IOException {
        //create new scanner and do any initialization tasks as needed
        System.out.println("Initializing...");
        Scanner in = new Scanner(System.in);

        // ask for number of bars
        //catch input as Integer and multiply by 16
        System.out.println("Number of Bars: ");
        Integer numberOfBars = in.nextInt()*16;

        //ask for filename to write to
        //catch input as String. Note all text files are stored in project root
        System.out.println("Filename: ");
        String filename = in.nextLine();

        //call write to file (will eventually change when we have agents to work with to accommodate multiple agents
        writeToFile(numberOfBars, filename);
        System.out.println("Done.");
    }
}
