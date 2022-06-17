package com.java;

interface One {

    void gerard();

    static void sample() {
        // similar to default, but cant be overriden
        System.out.printf("Cannot be overriden");
    }

    // a default interface method is a convenience method if there are frequently recurring default from the concrete classes
    default void print() {
        System.out.printf("print default interface method");
    }
}

@FunctionalInterface    // a functional interface is used by lambda expressions
interface Two {
    void doSomething(); // a functional interface has to have ONLY 1 abstract method, but can have default methods

    default void another() {
        System.out.printf("Another default");
    }
    default void print() {
        System.out.printf("Two");
    }
}

public abstract class Interfaces implements One, Two {

    public void callStaticInterface() {
        One.sample();   // only way to call static method, cannot call it from an instance
    }

    @Override
    public void print() {
        // overrides the implem of the default methods from both interfaces
        System.out.printf("concrete class");
        // and then calls them
        One.super.print();
        Two.super.print();
    }
}
