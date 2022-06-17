package com.patterns.behavioral;

import java.util.ArrayList;

/**
 * Motivation
 * Lets you save and restore the previous state of an object without revealing the details of its implementation
 *
 * Without violating encapsulation, capture and externalize an object's internal state so that the object can be returned to this state later
 * A magic cookie that encapsulate a "check point" capability
 * Promote undo/rollback to full object status
 *
 * References:
 * https://refactoring.guru/design-patterns/memento
 * https://sourcemaking.com/design_patterns/memento
 */

class MementoState {
    private String state;

    public MementoState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Originator {
    private String state;
    /* lots of memory consumptive private data that is not necessary to define the
     * state and should thus not be saved. Hence the small memento object. */

    public void setState(String state) {
        System.out.println("Originator: Setting state to " + state);
        this.state = state;
    }

    public MementoState save() {
        System.out.println("Originator: Saving to Memento.");
        return new MementoState(state);
    }
    public void restore(MementoState m) {
        state = m.getState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }
}

class Caretaker {
    private ArrayList<MementoState> mementos = new ArrayList<>();

    public void addMemento(MementoState m) {
        mementos.add(m);
    }

    public MementoState getMemento() {
        return mementos.get(1);
    }
}

public class Memento {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        originator.setState("State1");
        originator.setState("State2");
        caretaker.addMemento( originator.save() );
        originator.setState("State3");
        caretaker.addMemento( originator.save() );
        originator.setState("State4");
        originator.restore( caretaker.getMemento() );
    }
}
