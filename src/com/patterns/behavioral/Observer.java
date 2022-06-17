package com.patterns.behavioral;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Motivation
 * lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they're observing
 *
 * Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
 * Encapsulate the core (or common or engine) components in a Subject abstraction, and the variable (or optional or user interface) components in an Observer hierarchy.
 * The "View" part of Model-View-Controller.
 *
 * References
 * https://sourcemaking.com/design_patterns/observer
 * https://refactoring.guru/design-patterns/observer
 */
interface AlarmListener {
    void alarm();
}

class SensorSystem {
    private Vector listeners = new Vector();

    public void register(AlarmListener alarmListener) {
        listeners.addElement(alarmListener);
    }

    public void soundTheAlarm() {
        for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
            ((AlarmListener) e.nextElement()).alarm();
        }
    }
}

class Lighting implements AlarmListener {
    public void alarm() {
        System.out.println("lights up");
    }
}

class Gates implements AlarmListener {
    public void alarm() {
        System.out.println("gates close");
    }
}

class CheckList {
    // Template Method design pattern
    public void byTheNumbers() {
        localize();
        isolate();
        identify();
    }

    protected void localize() {
        System.out.println("   establish a perimeter");
    }

    protected void isolate() {
        System.out.println("   isolate the grid");
    }

    protected void identify() {
        System.out.println("   identify the source");
    }
}

// class inherit.
// type inheritance
class Surveillance extends CheckList implements AlarmListener {
    public void alarm() {
        System.out.println("Surveillance - by the numbers:");
        byTheNumbers();
    }

    protected void isolate() {
        System.out.println("   train the cameras");
    }
}


public class Observer {
    public static void main( String[] args ) {
        SensorSystem sensorSystem = new SensorSystem();
        sensorSystem.register(new Gates());
        sensorSystem.register(new Lighting());
        sensorSystem.register(new Surveillance());
        sensorSystem.soundTheAlarm();
    }
}
