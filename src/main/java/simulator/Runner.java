package main.java.simulator;

import main.java.util.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Runs the simulation.
 */
public class Runner {
    public static void main (String[] args) {
        System.out.println("Welcome to virtual memory simulator");
        System.out.println("Using file: " + args[0]);
        System.out.println("Allocation for Process 1: " + args[1]);
        System.out.println("Allocation for Process 2: " + args[2]);
        System.out.println("Allocation for Process 3: " + args[3]);
        System.out.println("Allocation for Process 4: " + args[4]);
        ArrayList<Object> parameters = validateArguments(args);
        if (!parameters.isEmpty()) {
            FileParser fp = new FileParser((File) parameters.get(0));
            try {
                System.out.println(fp.parseFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Arguments are not valid. Please see usage.");
        }
    }

    /**
     * Validates command line arguments to ensure they are within the expected range(s).
     * @param args Command line arguments to parse.
     * @return ArrayList of Objects containing each validated parameter in their given order
     * if parsed successfully, otherwise returns an empty ArrayList.
     */
    private static ArrayList<Object> validateArguments(String[] args) {
        ArrayList<Object> arguments = new ArrayList<>();
        boolean argumentsValid = true;

        String inputFileName = args[0];
        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            System.err.println(
                    "Provided argument for 'file name' is not valid. The file does not exist");
            argumentsValid = false;
        }
        else {
            arguments.add(inputFile);
        }

        if (argumentsValid) {
            for (int i = 1; i < args.length; i++) {
                arguments.add(Integer.parseInt(args[i]));
            }
        }

        return arguments;
    }
}
