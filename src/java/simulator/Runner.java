package java.simulator;

import java.io.File;
import java.util.ArrayList;

/**
 * Runs the simulation.
 */
public class Runner {
    public static void main (String[] args) {
        System.out.println("Welcome to virtual memory simulator");
    }

    /**
     * Validates command line arguments to ensure they are within the expected range(s).
     * @param args Command line arguments to parse.
     * @return ArrayList of Objects containing each validated parameter in their given order
     * if parsed successfully, otherwise returns an empty ArrayList.
     */
    private ArrayList<Object> validateArguments(String[] args) {
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
