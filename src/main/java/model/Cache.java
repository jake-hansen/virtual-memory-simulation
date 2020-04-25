package main.java.model;

import java.util.Queue;

public class Cache {

    private Queue<MemoryMap> queue;

    private int totalHits;

    private int totalMisses;

    private int size;

    public Cache(int size) {
        this.size = size;
    }

    public void process() {

    }
}
