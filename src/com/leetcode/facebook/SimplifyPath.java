package com.leetcode.facebook;

import java.util.LinkedList;

public class SimplifyPath {

    /*
    Maintain a linkedlist for the canonical path, and convert this into a string as a result.

    String[] absolte = path.split("/")

    Iterate over this so that each value can be parsed into a ., .., or a directory or file.
    We ignore the .
    We write the other entries into the linkedlist
    When we encounter .., we remove the last entry of the linkedlist.

    We construct the linkedlist into forming a canonical path.
     */
    public String simplifyPath(String path) {
        LinkedList<String> canonical = new LinkedList<>();
        String[] absolute = path.replaceAll("//", "/").split("/");
        for (String p : absolute) {
            if (p.equals(".") || p.length() < 1) {
                continue;
            }
            else if (p.equals("..")) {
                if (canonical.size() > 0) canonical.removeLast();
            }
            else {
                canonical.add(p);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String entry : canonical) {
            sb.append("/").append(entry);
        }
        return (sb.length() == 0 ? "/" : sb.toString());
    }
}
