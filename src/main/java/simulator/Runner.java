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

    private static FileParser fp;

    public static void main (String[] args) {
        System.out.println("Welcome to virtual memory simulator");
        ArrayList<Object> parameters = validateArguments(args);

        if (DEBUG) System.out.println("You are in Debug mode.");
        if (DEBUG) {
            System.out.println("Using file: " + parameters.get(0));
            for (int i = 1; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof Integer) {
                    System.out.println("Allocation size of process " + fp.getProcessNumbers().get(i - 1) + ": " + parameters.get(i));
                }
            }
        }

        if (!parameters.isEmpty()) {

            LinkedList<MemoryMap> mml = fp.getFileMemoryMap();
            if (DEBUG) {
                System.out.println(mml);
                System.out.println("Size of memory map: " + mml.size());
            }

            ArrayList<Cache> cacheList = new ArrayList<>();
            for (int i = 0; i < fp.getProcessNumbers().size(); i++) {
                cacheList.add(new Cache((int) parameters.get(i + 1), fp.getProcessNumbers().get(i)));
            }

            for (MemoryMap mm : mml) {
                cacheList.get(mm.getProcessNumber() - 1).process(mm);
            }

            double average = 0.0;
            for (Cache c : cacheList) {
                System.out.println(c);
                average += c.getRatio();
            }
            average /= cacheList.size();
            System.out.printf("Average: %3.2f%%", average * 100);

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

        if (args.length == 0 || args.length == 1) {
            System.err.println("Not enough arguments specified.");
            argumentsValid = false;
        } else {
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
                fp = new FileParser((File) arguments.get(0));
                try {
                    int numberOfProcesses = fp.parseProcessNumbers().size();
                    if (numberOfProcesses != (arguments.size() - 1)) {
                        System.err.println("Number of allocations does not match the " +
                                "number of processes in the given file. ");
                        argumentsValid = false;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!argumentsValid) arguments.clear();

        return arguments;
    }
}
