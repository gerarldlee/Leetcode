package com.algomonster.dc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Using the divide and conquer, merge sort algo
 */
public class Skyline {

    /*
    We are merging silhouette here, not buildings

    Left silhouette composed of top-left corner, and bottom-right corner. (Or can be a list of silhouettes)
    Right silhouette composed of top-left corner, and bottom-right corner. (Or can be a list of silhouettes)

    To get the silhouette points of these 2:
    Determine (x, y)
    - iterate through Left and Right silhouettes
        - comparing one Left and one Right,
        - getting its leftest left = x = Math.min(leftX, rightX)
        - and its corresponding height, = y = if leftX < rightY, then y = leftY, else rightY.
        Because the height in the leftest left does not match the other
        - However, that's only true in the first iteration where we are getting the leftest border. In the next iteration,
        we are determining the y= Math.max(previousMaxHeight, currentHeightOfTheLeftest)

     */
    public static List<List<Integer>> mergeSilhouette(List<List<Integer>> leftSilhouette, List<List<Integer>> rightSilhouette) {
        List<List<Integer>> shorter = leftSilhouette;
        List<List<Integer>> larger = rightSilhouette;
        if (shorter.size() > larger.size()) {
            shorter = rightSilhouette;
            larger = leftSilhouette;
        }

        int shortIndex=0, largeIndex=0;
        int shortHeight=0, largeHeight=0;
        int prevX=0, prevY=0;
        List<List<Integer>> result = new ArrayList<>();
        while (largeIndex < larger.size()) {
            int largeX = larger.get(largeIndex).get(0);
            int largeY = larger.get(largeIndex).get(1);
            if (shortIndex >= shorter.size()) { // just put the larger since we are done with the shorter
                if (prevX != largeX && prevY != largeY)
                    result.add(List.of(largeX, largeY));
                prevX = largeX;
                prevY = largeY;
                largeIndex++;
            }
            else {
                int shortX = shorter.get(shortIndex).get(0);
                int shortY = shorter.get(shortIndex).get(1);
                int x = Math.min(shortX, largeX);
                if (shortX < largeX) {
                    shortHeight = shortY;
                    shortIndex++;
                }
                else {
                    largeHeight = largeY;
                    largeIndex++;
                }
                int y = Math.max(shortHeight, largeHeight);
                if (prevX != x && prevY != y)
                    result.add(List.of(x, y));
                prevX = x;
                prevY = y;
            }
        }
        return result;
    }

    /*
    Let's suppose there's a list of buildings, and each building have x (left border), z (right border), y (height)
    If theres one bldg, the point is (x, y) and (z, 0)
     */
    public static List<List<Integer>> divideBuildings(List<Building> buildings) {

        if (buildings.size() == 0) return Arrays.asList();
        if (buildings.size() == 1) {
            // returns the silhouette of 1 building
            return Arrays.asList(Arrays.asList(buildings.get(0).left, buildings.get(0).height),
                    Arrays.asList(buildings.get(0).right, 0));
        }

        int mid = buildings.size()/2;
        List<Building> leftCopy = new ArrayList<>(buildings.subList(0, mid));
        List<Building> rightCopy = new ArrayList<>(buildings.subList(mid, buildings.size()));

        List<List<Integer>> left = divideBuildings(leftCopy);
        List<List<Integer>> right = divideBuildings(rightCopy);

        return mergeSilhouette(left, right);
    }

    public static List<List<Integer>> getSkyline(List<List<Integer>> buildings) {
        List<Building> buildings1 = new ArrayList<>();
        for (List<Integer> b : buildings) {
            buildings1.add(new Building(b));
        }
        return divideBuildings(buildings1);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        List<List<Integer>> buildings = new ArrayList<>();
        String[] building = {"2 9 10", "3 7 15", "5 12 12", "15 20 10", "19 24 8"};
        for (String b : building) {
            buildings.add(splitWords(b).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }

        List<List<Integer>> res = getSkyline(buildings);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}

class Building {
    int left, height, right;
    public Building(List<Integer> array) {
        left = array.get(0);
        right = array.get(1);
        height = array.get(2);
    }
}