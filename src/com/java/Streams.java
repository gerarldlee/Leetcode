package com.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Wrappers around a datasource, that allows us to operate with that datasource, and make bulk processing convenient and fast
 * - supports functional style operations on stream of elements, i.e. map-reduce transformations, filter-terminal operations on collections
 *
 * Data also become immutable,
 */

public class Streams {
    /* Stream Creation */
    public void createStream() throws IOException {
        // empty stream
        Stream<String> emptyStream = Stream.empty();
        // stream from list
        List<String> list = new ArrayList<>();
        Stream<String> listStream = list.stream();
        // stream from array
        Stream<Integer> intStream = Stream.of(1,2,3);
        Stream<String> stringArrayStream = Arrays.stream(new String[] {"a", "b", "c"});
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();
        // stream from generated elements
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
        // stream from iterate method
        Stream<Integer> integerStream = Stream.iterate(40, n-> n+2).limit(20);
        // stream from primitive
        IntStream intStreams = IntStream.range(1, 3);
        LongStream longStream = LongStream.rangeClosed(1, 3);
        // file stream
        Path path = Paths.get("C:\\file.txt");
        Stream<String> streamOfStrings = Files.lines(path);
        Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));

    }

    /* Reference a stream */
    public void referenceStream() {
        // not possible to reference a stream as it cannot be reused, immutable
        // have to put terminal operation i.e. collect
        List<String> elements = Stream.of("a", "b", "c").filter(element -> element.contains("b"))
                        .collect(Collectors.toList());
        Optional<String> anyElement = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();
    }

    /* Stream pipeline */
    public void pipelineStream() {
        // the skip() method calls for the next stream intermediate operation
        Stream<String> twiceModifiedStream = Stream.of("abcd", "bbcd").skip(1).map(element -> element.substring(0, 3));

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1)
                .map(element -> element.substring(0, 3)).sorted().count();
    }

    /* lazy invocation */
    public void lazyInvocation() {
        // streams are only executed once the terminal operation is called
        // it also wont process any terminal operation i.e. sout, inside the intermediate operation
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        AtomicInteger counter = new AtomicInteger();
        Stream<String> stream = list.stream().filter(element -> {
            counter.getAndIncrement();
            return element.contains("2");
        });

        Optional<String> stream1 = list.stream().filter(element -> {
//            log.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
//            log.info("map() was called");
            return element.toUpperCase();
        }).findFirst();
    }

    /* Order of execution */
    public void orderOfExecution() {
        // intermediate operations which reduce the size of the stream should be placed before operations
        // which are applied to each element.
    }

    /* Stream reduction */
    public void streamReduction() {
        // reduce method
        OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b);
        int reducedTwoParams = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        int reducedParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
//                    log.info("combiner was called");
                    return a + b;
                });
        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
//                    log.info("combiner was called");
                    return a + b;
                });

        // collect method
        List<String> productList = Arrays.asList(new String[] {"orange", "lemon"});
        List<String> collectorCollection = productList.stream().collect(Collectors.toList());
        // reduce to 1 string
        String listToString = productList.stream().collect(Collectors.joining(", ", "[", "]"));

        List<Integer> productPrices = List.of(1,2,3);
//        int summingPrice = productPrices.stream().collect(Collectors.summingInt());
    }

    /* Parallel streams */
    public void parallelStream() {
        // under the hood, it uses ForkJoin framework to execute operations in parallel

        IntStream intStreamParallel = IntStream.range(1, 150).parallel();
        boolean isParallel = intStreamParallel.isParallel();
    }

    /* intermediate operatations */
    public void intermediateOperations() {
        /*
        filter() - filters a stream from a predicate
        map() - converts the current stream and creates a new stream
        flatMap() - flattens the stream of collection to a stream of objects
        distinct() - returns a new stream containing distinct elements
        sorted() - sort elements by comparator
        peek() - supports debugging by letting us see elements as they flow into the pipeline, returns new stream
        limit() - returns new stream containing elements limited by given size
        skip() - skips the first n elements, returns new stream containing remaining elements
         */
    }

    /* terminal operations */
    public void terminalOperations() {
        /*
        forEach() - iterate over all elements, perform consumer action
        forEachOrdered() - forEach() but in an encounter order if the Stream has a defined one. (used for parallel stream)
        toArray() - converts a stream to array
        reduce() -
        collect()
        min() - selects the minimum / the smallest element acdg to comparator
        max()
        count() - counts the matching elements in the stream
        anyMatch() - short circuit to check at least one element satisfies given predicate
        noneMatch() - checks that stream contains none of given predicate
        allMatch() - checks all element must satisfy given predicate
        findFirst() - returns the first element of the stream
        findAny() - any elements of the stream

        reduce() - s a "fold" operation, it applies a binary operator to each element in the stream where the first
        argument to the operator is the return value of the previous application and the second argument is the current
        stream element.

        collect() is an aggregation operation where a "collection" is created and each element is "added" to that
        collection. Collections in different parts of the stream are then added together.

        MUTABLE REDUCTION: collect() vs reduce()
        ================================================================================================================
        If we wanted to take a stream of strings and concatenate them into a single long string,
        we could achieve this with ordinary reduction: (reduce())

         String concatenated = strings.reduce("", String::concat)

        We would get the desired result, and it would even work in parallel. However, we might not be happy about the
        performance! Such an implementation would do a great deal of string copying, and the run time would be O(n^2)
        in the number of characters. A more performant approach would be to accumulate the results into a StringBuilder,
        which is a mutable container for accumulating strings. We can use the same technique to parallelize mutable
        reduction as we do with ordinary reduction.

        So the point is that the parallelization is the same in both cases but in the reduce() case we apply the function
        to the stream elements themselves. In the collect case we apply the function to a mutable container.

        List<String> strings = stream.map(Object::toString)
                                  .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        Or in a parallelized form:
        ArrayList<String> strings = stream.collect(() -> new ArrayList<>(),
                                                (c, e) -> c.add(e.toString()),
                                                (c1, c2) -> c1.addAll(c2));

        List<String> list = Arrays.asList("Mike", "Nicki", "John");
        String s = list.stream().collect(StringBuilder::new,
                            (sb, s1) -> sb.append(" ").append(s1),
                            (sb1, sb2) -> sb1.append(sb2.toString())).toString();
         */
    }
}
