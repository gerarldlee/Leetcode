package com.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

public class Test {


    public static void main(String[] a) {

        Map<Point, String> map = new HashMap<>();

        Point p = new Point(0.0, 0.0);

        map.put(p, "value");

        p.setX(2.0);

        System.out.println(map.get(p));

    }
}
