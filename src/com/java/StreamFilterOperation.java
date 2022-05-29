package com.java;

import java.util.stream.IntStream;

public class StreamFilterOperation {

    public static void main(String[] a) {

        // System out is not performed, and all filter is not performed until a terminal operation is invoked
        // Java stream is lazily evaluated

        IntStream.range(0,10).filter(x -> {
            boolean b = x > 0;
            System.out.println(b);
            return b;
        }).reduce(0, Integer::sum);

    }
}
