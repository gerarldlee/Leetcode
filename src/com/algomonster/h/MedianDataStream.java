package com.algomonster.h;

import java.util.PriorityQueue;

/*
The idea is we only need the median:
We need to get the min of the numbers > median
We need to get the max of the numbers < median
We need to average them out to get the median

Then we need to re-balance it, by:
if the lower numbered elements < higher numbered elements, then move the top value from higher numbered elements to lower
if the lower numbered elements > higher numbered elements, then move the top value from the lower numbered elements to higher
 */
public class MedianDataStream {

    PriorityQueue<Integer> higherElements = new PriorityQueue<>();
    PriorityQueue<Integer> lowerElements = new PriorityQueue<>((a, b) -> b-a);

    public void addNum(int num) {
        if (higherElements.isEmpty() || num < higherElements.peek()) {
            lowerElements.add(num);
        }
        else {
            higherElements.add(num);
        }

        if (lowerElements.size() < higherElements.size()) {
            lowerElements.add(higherElements.poll());
        }
        if (lowerElements.size() > higherElements.size() + 1) {
            higherElements.add(lowerElements.poll());
        }
    }

    public double getMedian() {
        if (lowerElements.size() == higherElements.size()) {
            return (lowerElements.peek() + higherElements.peek()) / 2.0;
        }
        return lowerElements.peek();
    }

}
