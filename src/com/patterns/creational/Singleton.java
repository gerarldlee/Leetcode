package com.patterns.creational;

/**
 * Motivation:
 * Ensures a class has only one instance and provide a global point of access to it
 * Encapsulates just-in-time initialization or lazy-initialization on first use
 *
 * References:
 * https://sourcemaking.com/design_patterns/singleton
 * https://refactoring.guru/design-patterns/singleton
 */
public class Singleton {
    private Singleton() {}

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

final class ThreadsafeSingleton {
    private static volatile ThreadsafeSingleton instance;
    public String value;

    private ThreadsafeSingleton(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static synchronized ThreadsafeSingleton getInstance(String value) {
        if (instance == null) {
            instance = new ThreadsafeSingleton(value);
        }
        return instance;
    }
}