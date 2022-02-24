package com.leetcode.queuestack;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageStream {

    int size = 0;
    Queue<Integer> queue = null;
    double running_sum = 0;
    double running_counter = 0;

    public MovingAverageStream(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    // time: o(1), space: o(size)
    public double next(int val) {
        queue.add(val);
        running_sum += val;
        running_counter++;

        if (running_counter > size) {
            int topValue = queue.poll();
            running_sum -= topValue;
            running_counter--;
        }

        return running_sum / running_counter;
    }
}
