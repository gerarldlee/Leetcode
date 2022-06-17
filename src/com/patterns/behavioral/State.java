package com.patterns.behavioral;

/**
 * Motivation
 * Lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.
 *
 * Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.
 * An object-oriented finite-state machine
 * wrapper + polymorphic wrappee + collaboration
 *
 * References:
 * https://sourcemaking.com/design_patterns/state
 * https://refactoring.guru/design-patterns/state
 */
// 1. Create a "wrapper" class that models the state machine
class FSM {
    // 2. states
    private StateIF[] states = {new A(), new B(), new C()};
    // 4. transitions
    private int[][] transition = {{2,1,0}, {0,2,1}, {1,2,2}};
    // 3. current
    private int current = 0;

    private void next(int msg) {
        current = transition[current][msg];
    }

    // 5. All client requests are simply delegated to the current state object
    public void on() {
        states[current].on();
        next(0);
    }

    public void off() {
        states[current].off();
        next(1);
    }

    public void ack() {
        states[current].ack();
        next(2);
    }
}

// 6. Create a state base class that makes the concrete states interchangeable
// 7. The State base class specifies default behavior
abstract class StateIF {
    public void on() {
        System.out.println("error");
    }

    public void off() {
        System.out.println("error");
    }

    public void ack() {
        System.out.println("error");
    }
}

class A extends StateIF {
    public void on() {
        System.out.println("A + on  = C");
    }

    public void off() {
        System.out.println("A + off = B");
    }

    public void ack() {
        System.out.println("A + ack = A");
    }
}

class B extends StateIF {
    public void on() {
        System.out.println("B + on  = A");
    }

    public void off() {
        System.out.println("B + off = C");
    }
}

class C extends StateIF {
    // 8. The State derived classes only override the messages they need to
    public void on() {
        System.out.println("C + on  = B");
    }
}

public class State {
    public static void main(String[] args) {
        DistributedFSM fsm = new DistributedFSM();
        int[] msgs = {2, 1, 2, 1, 0, 2, 0, 0};
        for (int msg : msgs) {
            if (msg == 0) {
                fsm.on();
            } else if (msg == 1) {
                fsm.off();
            } else if (msg == 2) {
                fsm.ack();
            }
        }
    }
}
