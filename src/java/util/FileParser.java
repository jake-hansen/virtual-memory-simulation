package java.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.model.MemoryMap;

/** Utilities for parsing input file. **/
public class FileParser {

    /** File to parse. **/
    private File inputFile;

    /**
     * Default constructor sets input file.
     *
     * @param inputFile File to parse.
     */
    public FileParser(File inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Parses file for memory mappings. File needs to have each memory mapping
     * on a separate line. Each line should be of the format "PROCESS# FRAME#"
     * where PROCESS# and FRAME# are the process number and frame number,
     * respectively.
     *
     * @return LinkedList<MemoryMap> containing memory mapping from file.
     * @throws FileNotFoundException Thrown when file is not found.
     */
    public LinkedList<MemoryMap> parseFile() throws FileNotFoundException {
        LinkedList<MemoryMap> returnMap = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(this.inputFile));

        return returnMap;
    }
}
