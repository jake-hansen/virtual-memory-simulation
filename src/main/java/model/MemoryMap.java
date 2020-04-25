package main.java.model;

/**
 * Represents a mapping of process numbers to frame numbers.
 */
public class MemoryMap {

    /** Represents process number. **/
    private final int processNumber;

    /** Represents frame number. **/
    private final int frameNumber;

    /**
     * Default constructor sets processNumber and frameNumber to specified values.
     *
     * @param processNumber Process number.
     * @param frameNumber Frame number.
     */
    public MemoryMap(int processNumber, int frameNumber) {
        this.processNumber = processNumber;
        this.frameNumber = frameNumber;
    }

    /**
     * Gets the process number.
     *
     * @return Int representing the process number.
     */
    public int getProcessNumber() {
        return processNumber;
    }

    /**
     * Gets the frame number.
     *
     * @return Int representing the frame number.
     */
    public int getFrameNumber() {
        return frameNumber;
    }
}
