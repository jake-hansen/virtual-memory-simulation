package main.java.simulator;

import main.java.model.Cache;
import main.java.model.MemoryMap;
import main.java.util.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Runs the simulation.
 */
public class Runner {

    /** Keeps track of debug status. **/
    public static boolean DEBUG = false;

    public static void main (String[] args) {
        ArrayList<Object> parameters = validateArguments(args);

        System.out.println("Welcome to virtual memory simulator");
        if (DEBUG) System.out.println("You are in Debug mode.");
        if (DEBUG) {
            System.out.println("Using file: " + parameters.get(0));
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof Integer) {
                    System.out.println("Allocation size of process " + i + ": " + parameters.get(i));
                }
            }
        }

        if (!parameters.isEmpty()) {
            FileParser fp = new FileParser((File) parameters.get(0));
            try {
                LinkedList<MemoryMap> mml = fp.parseFile();
                if (DEBUG) {
                    System.out.println(mml);
                    System.out.println("Size of memory map: " + mml.size());

                    Cache process1Cache = new Cache((int) parameters.get(1));

                    for (MemoryMap mm : mml) {
                        if (mm.getProcessNumber() == 1) {
                            process1Cache.process(mm);
                        }
                    }

                    System.out.println(process1Cache.getRatio());
                }
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
                if (args[i].equals("--debug")) {
                    DEBUG = true;
                } else {
                    arguments.add(Integer.parseInt(args[i]));
                }
            }
        }

        if (argumentsValid) {
            FileParser fp = new FileParser((File) arguments.get(0));
            try {
                int numberOfProcesses = fp.getProcessNumbers().size();
                if (numberOfProcesses != (arguments.size() - 1)) {
                    System.err.println("Number of allocations does not match the " +
                            "number of processes in the given file. ");
                    argumentsValid = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!argumentsValid) arguments.clear();

        return arguments;
    }
}
