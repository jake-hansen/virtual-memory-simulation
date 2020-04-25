package main.java.model;

import java.util.LinkedList;
import java.util.Queue;

public class Cache {

    /** Queue to hold MemoryMaps. **/
    private final Queue<MemoryMap> queue;

    /** Total number of cache hits. **/
    private int totalHits = 0;

    /** Total number of cache misses. **/
    private int totalMisses = 0;

    /** Size limit for cache. **/
    private final int size;

    /**
     * Constructor for cache that takes size limit for cache as parameter.
     *
     * @param size Size limit for cache.
     */
    public Cache(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    /**
     * Processes a request for a frame. Uses LRU replacment policy.
     *
     * @param mm MemoryMap to process.
     */
    public void process(MemoryMap mm) {
        if (queue.contains(mm)) {
            queue.remove(mm);
            totalHits++;
        } else {
            if (queue.size() == size) {
                queue.remove();
            }
            totalMisses++;
        }
        queue.add(mm);
    }

    /**
     * Calculate hit ratio.
     *
     * @return Hit ratio.
     */
    public double getRatio() {
        return (double) totalHits / ((double) totalMisses + (double) totalHits);
    }
}
