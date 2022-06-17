package com.patterns.creational;

import java.util.HashMap;
import java.util.Map;

/**
 * Motivation:
 * Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype
 * Co-opt one instance of a class for use as a breeder of all future instances
 * New operator considered harmful
 *
 * References:
 * https://refactoring.guru/design-patterns/prototype
 * https://sourcemaking.com/design_patterns/prototype
 */
interface Person {
    Person clone();
}

class Tom implements Person {
    private final String NAME = "Tom";

    @Override
    public Person clone() {
        return new Tom();
    }

    @Override
    public String toString() {
        return NAME;
    }
}

class Dick implements Person {
    private final String NAME = "Dick";

    @Override
    public Person clone() {
        return new Dick();
    }

    @Override
    public String toString() {
        return NAME;
    }
}

class Harry implements Person {
    private final String NAME = "Harry";

    @Override
    public Person clone() {
        return new Harry();
    }

    @Override
    public String toString() {
        return NAME;
    }
}

class PrototypeFactory {
    private static final Map<String, Person> prototypes = new HashMap<>();

    static {
        prototypes.put("tom", new Tom());
        prototypes.put("dick", new Dick());
        prototypes.put("harry", new Harry());
    }

    public static Person getPrototype(String type) {
        try {
            return prototypes.get(type).clone();
        } catch (NullPointerException ex) {
            System.out.println("Prototype with name: " + type + ", doesn't exist");
            return null;
        }
    }
}

public class Prototype {
    public static void main(String[] args) {
        if (args.length > 0) {
            for (String type : args) {
                Person prototype = PrototypeFactory.getPrototype(type);
                if (prototype != null) {
                    System.out.println(prototype);
                }
            }
        } else {
            System.out.println("Run again with arguments of command string ");
        }
    }
}
