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

    /**
     * Returns string representation of object.
     *
     * @return String containing process number and frame number.
     */
    @Override
    public String toString() {
        return String.format("Process number %s, Frame Number: %s", this.processNumber, this.frameNumber);
    }

    /**
     * Compares Object with this to test if they are equal. Two MemoryMap objects
     * are said to be equal if their frame numbers and process numbers are the same.
     * @param o Object to compare to.
     * @return True if o and this instance are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof MemoryMap)) {
            return false;
        }

        MemoryMap c = (MemoryMap) o;

        return Integer.compare(c.frameNumber, this.frameNumber) == 0 &&
                Integer.compare(c.processNumber, this.processNumber) == 0;
    }
}
