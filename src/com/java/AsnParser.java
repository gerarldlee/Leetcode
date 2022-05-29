package com.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AsnParser {

    public static List<String> getFileContent() throws IOException {
        List<String> content = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (!line.equals("EOF")) {
            content.add(line);
            line = br.readLine();
        }
        return content;
    }

    /*
        Adds parentId to every record

        Basically iterates the stack to see if there are the same levelType in the stack
        - if there are none,
            - then just add the lineRecord to the stack, and attach the id of the previous one in the stack
        - if there is, then pop the stack until there is none, and do the same as above

Example input:
10
1:T:Fedex #100
2:O:ORD1230
3:P:Honey Dishwash 1L,200 pieces
4:P:Blueberry Dishwasher 1L,100 pieces
5:O:ORD4590
6:P:Honey Dishwash 1L,120 pieces
7:T:Fedex #200
8:O:ORD3405
9:P:Honey Dishwash 1L,150 pieces
10:P:Blueberry Dishwash 1L,90 pieces

Expected output:
0:1:T:Fedex #100
1:2:O:ORD1230
2:3:P:Honey Dishwash 1L,200 pieces
2:4:P:Blueberry Dishwasher 1L,100 pieces
1:5:O:ORD4590
5:6:P:Honey Dishwash 1L,120 pieces
0:7:T:Fedex #200
7:8:O:ORD3405
8:9:P:Honey Dishwash 1L,150 pieces
8:10:P:Blueberry Dishwash 1L,90 pieces

        Time: O(N) - iterates through the records
        Space: O(N)
     */
    public static List<Record> addParents(List<String> records) {
        List<Record> results = new ArrayList<>();
        Stack<Record> parents = new Stack<>();

        for (int index=0; index<records.size(); index++) {
            Record lineRecord = new Record(records.get(index));

            int stackLevel = 0;
            while (stackLevel < parents.size()) {
                Record current = parents.get(stackLevel);
                if (current.levelType.equals(lineRecord.levelType))
                    parents.pop();
                else
                    stackLevel++;
            }

            // base case when there are no stack, just push the lineRecord.
            // the lineRecord has a parentId default value of 0
            if (parents.size() == 0) {
                parents.push(lineRecord);
            }
            else {
                Record parent = parents.peek();
                lineRecord.parentId = parent.id;
                parents.push(lineRecord);
            }
            results.add(lineRecord);
        }
        return results;
    }

    public static void main(String[] a) throws Exception {
//        List<String> content = getFileContent();

        List<String> content = new ArrayList<>();
        content.add("1:T:Fedex #100");
        content.add("2:O:ORD1230");
        content.add("3:P:Honey Dishwash 1L,200 pieces");
        content.add("4:P:Blueberry Dishwasher 1L,100 pieces");
        content.add("5:O:ORD4590");
        content.add("6:P:Honey Dishwash 1L,120 pieces");
        content.add("7:T:Fedex #200");
        content.add("8:O:ORD3405");
        content.add("9:P:Honey Dishwash 1L,150 pieces");
        content.add("10:P:Blueberry Dishwash 1L,90 pieces");

        // write your code here
        List<Record> results = addParents(content);
        for (Record line : results) {
            System.out.println(line);
        }
    }

    public static class Record {
        short parentId;
        short id;
        String levelType;
        String desc;
        List<Record> children = new ArrayList<>();
        public Record(String str) {
            String[] s = str.split(":", 3);
            this.id = Short.parseShort(s[0]);
            this.levelType = s[1];
            this.desc = s[2];
        }
        @Override
        public String toString() {
            return String.format("%d:%d:%s:%s", parentId, id, levelType.toUpperCase(), desc);
        }
    }

}
