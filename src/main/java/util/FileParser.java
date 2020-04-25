package main.java.util;

import java.io.*;
import main.java.model.MemoryMap;

import java.util.ArrayList;
import java.util.LinkedList;

/** Utilities for parsing input file. **/
public class FileParser {

    /** File to parse. **/
    private final File inputFile;

    private final LinkedList<MemoryMap> fileMemoryMap;

    private final ArrayList<Integer> processNumbers;

    /**
     * Default constructor sets input file.
     *
     * @param inputFile File to parse.
     */
    public FileParser(File inputFile) {
        this.inputFile = inputFile;
        fileMemoryMap = new LinkedList<>();
        processNumbers = new ArrayList<>();
    }

    /**
     * Parses file for memory mappings. File needs to have each memory mapping
     * on a separate line. Each line should be of the format "PROCESS# FRAME#"
     * where PROCESS# and FRAME# are the process number and frame number,
     * respectively.
     *
     * @return LinkedList<MemoryMap> containing memory mapping from file.
     * @throws FileNotFoundException Thrown when file is not found.
     * @throws IOException
     */
    public LinkedList<MemoryMap> parseFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.inputFile));

        String line;
        while ((line = br.readLine()) != null) {
            int processNumber, frameNumber;
            String[] split = line.split("\\s+");
            processNumber = Integer.parseInt(split[0]);
            frameNumber = Integer.parseInt(split[1]);
            MemoryMap mm = new MemoryMap(processNumber, frameNumber);
            fileMemoryMap.add(mm);
        }

        return fileMemoryMap;
    }

    /**
     * Retrieves all the unique process numbers for the given file.
     * @return List of unique process numbers.
     * @throws IOException Thrown if there is an error parsing the file.
     */
    public ArrayList<Integer> parseProcessNumbers() throws IOException {
        if (fileMemoryMap.isEmpty()) {
            parseFile();
        }
        for (MemoryMap mm : fileMemoryMap) {
            if (!processNumbers.contains(mm.getProcessNumber())) {
                processNumbers.add(mm.getProcessNumber());
            }
        }

        return processNumbers;
    }

    public LinkedList<MemoryMap> getFileMemoryMap() {
        return fileMemoryMap;
    }

    public ArrayList<Integer> getProcessNumbers() {
        return processNumbers;
    }
}
