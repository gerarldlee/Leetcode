package com.algomonster.ads;

import java.util.*;

class User implements Comparable<User> {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object userB) {
        if (userB == null) return false;
        User user = (User) userB;
        return user.name.equals(this.name) && user.email.equals(this.email);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() ^ this.email.hashCode();
    }

    @Override
    public int compareTo(User o) {
        return this.email.compareTo(o.email);
    }
}

class DisjointSet<T> {
    private HashMap<T, T> id = new HashMap<>();
    public T find(T x) {
        T y = id.getOrDefault(x, x);
        while (y != x) {
            y = find(y);
            id.put(x, y);
        }
        return y;
    }
    public void union(T x, T y) {
        id.put(find(x), find(y));
    }
}

public class MergeUserAccounts {

    public static List<List<String>> mergeAccounts(List<List<String>> accounts) {

        // the idea is to iderate through acounts
        // create the first 2 element as name and email and as temporary parent in the disjoint set
        // then merge the succeeding emails linked to the parent

        // on the next account, do the same, create a temporary parent
        // and link the succeeding emails
        // but check if the user/email already exist in the disjoint set.
        // if it does, then link the parent to it

        DisjointSet<User> connect = new DisjointSet<>();
        SortedSet<User> users = new TreeSet<>();
        for (List<String> account : accounts) {
            String username = account.get(0);
            User representative = null;
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                User child = new User(username, email);
                users.add(child);
                if (representative == null) {
                    representative = child;
                    connect.union(child, representative);
                    continue;
                }
                if (connect.find(child) != null) {
                    User parent = connect.find(child);
                    connect.union(representative, parent);
                    connect.union(child, parent);
                    representative = parent;
                }
            }
        }

        // after everything is setup in the disjoint set
        // we need to traverse over all of the accounts
        // find the parent of each account, put all the children there
        SortedMap<User, List<User>> sortedMap = new TreeMap<>();
        for (User user : users) {
            User parent = connect.find(user);
            if (sortedMap.containsKey(parent)) {
                sortedMap.get(parent).add(user);
            } else {
                sortedMap.put(parent, List.of(user));
            }
        }

        // finally, arrange this into a sorted list
        List<List<String>> result = new ArrayList<>();
        for (List<User> values : sortedMap.values()) {
            ArrayList<String> emails = new ArrayList<>();
            emails.add(values.get(0).name);
            for (User u : values) {
                emails.add(u.email);
            }
            result.add(emails);
        }

        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int accountsLength = Integer.parseInt(scanner.nextLine());
        List<List<String>> accounts = new ArrayList<>();
        for (int i = 0; i < accountsLength; i++) {
            accounts.add(splitWords(scanner.nextLine()));
        }
        scanner.close();
        List<List<String>> res = mergeAccounts(accounts);
        for (List<String> row : res) {
            System.out.println(String.join(" ", row));
        }
    }
}
