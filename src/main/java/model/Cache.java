package main.java.model;

import java.util.LinkedList;
import java.util.Queue;

public class Cache {

    private Queue<MemoryMap> queue;

    private int totalHits = 0;

    private int totalMisses = 0;

    private int size;

    public Cache(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

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

    public double getRatio() {
        return (double) totalHits / ((double) totalMisses + (double) totalHits);
    }
}
