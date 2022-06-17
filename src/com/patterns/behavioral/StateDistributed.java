package com.patterns.behavioral;

/**
 * Another example of a distributed finite-state machine
 *
 */

// 1. The "wrapper"
class DistributedFSM {
    // 2. States array
    private DistributedState[] states  = {new DistributedA(), new DistributedB(), new DistributedC()};

    // 3. Current state
    private int currentState = 0;

    // 4. Delegation and pass the this pointer
    public void on()  {
        states[currentState].on(this);
    }

    public void off() {
        states[currentState].off(this);
    }

    public void ack() {
        states[currentState].ack(this);
    }

    public void changeState(int index) {
        currentState = index;
    }
}

// 5. The State base class
abstract class DistributedState {
    // 6. Default behavior
    public void on(DistributedFSM fsm) {
        System.out.println("error");
    }

    public void off(DistributedFSM fsm) {
        System.out.println("error");
    }

    public void ack(DistributedFSM fsm) {
        System.out.println("error");
    }
}

class DistributedA extends DistributedState {
    public void on(  DistributedFSM fsm ) {
        System.out.println("A + on  = C");
        fsm.changeState(2);
    }

    public void off(DistributedFSM fsm) {
        System.out.println("A + off = B");
        fsm.changeState(1);
    }

    public void ack(DistributedFSM fsm) {
        System.out.println("A + ack = A");
        fsm.changeState(0);
    }
}

class DistributedB extends DistributedState {
    public void on(DistributedFSM fsm) {
        System.out.println("B + on  = A");
        fsm.changeState(0);
    }

    public void off(DistributedFSM fsm) {
        System.out.println("B + off = C");
        fsm.changeState(2);
    }
}

// 7. Only override some messages
class DistributedC extends DistributedState {
    // 8. "call back to" the wrapper class
    public void on(DistributedFSM fsm) {
        System.out.println("C + on  = B");
        fsm.changeState(1);
    }
}

public class StateDistributed {
    public static void main(String[] args) {
        DistributedFSM fsm  = new DistributedFSM();
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
