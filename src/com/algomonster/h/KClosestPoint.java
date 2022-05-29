package com.algomonster.h;

import java.util.*;
import java.util.stream.Collectors;

public class KClosestPoint {

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public static List<List<Integer>> kClosestPoints(List<List<Integer>> points, int k) {
        // WRITE YOUR BRILLIANT CODE HERE
        Comparator<Point> pointComparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int p1 = (int) Math.sqrt(Math.pow(o1.x, 2) + Math.pow(o1.y, 2));
                int p2 = (int) Math.sqrt(Math.pow(o2.x, 2) + Math.pow(o2.y, 2));
                return p1 - p2;
            }
        };

        PriorityQueue<Point> heap = new PriorityQueue<>(pointComparator);
        for (List<Integer> point : points) {
            heap.add(new Point(point.get(0), point.get(1)));
        }
        List<List<Integer>> res = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            Point p = heap.poll();
            res.add(List.of(p.x, p.y));
        }
        return res;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pointsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> points = new ArrayList<>();
        for (int i = 0; i < pointsLength; i++) {
            points.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<List<Integer>> res = kClosestPoints(points, k);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
