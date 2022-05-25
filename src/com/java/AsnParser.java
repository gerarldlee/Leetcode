package com.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AsnParser {

    public static List<String> getFileContent() throws IOException {
        List<String> content = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (!line.equals("null")) {
            content.add(line);
            line = br.readLine();
        }
        return content;
    }

    public static void main(String[] a) throws Exception {
        List<String> content = getFileContent();

        // write your code here
        // TODO needs to be implemented using topological sort
        int records = Short.parseShort(content.get(0));
        Record parent = null;
        Record lastorder = null;
        for (int i=1; i<=records; i++) {
            String line = content.get(i);
            Record r = new Record(line, parent, lastorder);
            if (r.isParent()) {
                parent = r;
            }
            else if (r.isOrder()) {
                lastorder = r;
            }
            System.out.println(r);
        }
    }

    public static class Record {
        short parentId;
        short id;
        String levelType;
        String desc;
        public Record(String str, Record parent, Record lastorder) {
            String[] s = str.split(":", 3);
            this.id = Short.parseShort(s[0]);
            this.levelType = s[1];
            this.desc = s[2];

            if (parent != null && this.levelType.equals("O")) {
                this.parentId = parent.id;
            }

            if (lastorder != null && this.levelType.equals("P")) {
                this.parentId = lastorder.id;
            }

        }

        public boolean isParent() {
            // if levelType is not O, P or I, then its a parent
            if (this.levelType.equals("O") || this.levelType.equals("P") || this.levelType.equals("I")) {
                return false;
            }
            return true;
        }

        public boolean isOrder() {
            if (this.levelType.equals("O")) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("%d:%d:%s:%s", parentId, id, levelType.toUpperCase(), desc);
        }
    }

    /*
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


     */
}
