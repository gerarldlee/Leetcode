package com.patterns.concurrency;

import java.util.concurrent.Executors;

/**
 * Pattern: Protected Shared State
 *
 * Motivations: When you have a shared object passed through threads, you need
 * to protect it's internal state.
 *
 * Intent: Guard the shared mutable state of the object with a lock using the
 * synchronized mechanism. Protect all paths that interacts with the object
 * state, creating a thread safe class. We do this to avoid hazards like race
 * conditions.
 *
 * Applicability: Simples classes where you have a single independent mutable
 * variable(s).
 *
 * Example: A simple counter class
 *
 */
@ThreadSafe
class ProtectedSharedState {
    @GuardedBy("this")
    private Object state;

    @GuardedBy("this")
    private Object state2;

    public synchronized Object getState() {
        return state;
    }

    public synchronized void setState(Object state) {
        this.state = state;
    }

    public synchronized Object getState2() {
        return state2;
    }

    public synchronized void setState2(Object state2) {
        this.state2 = state2;
    }
}


/**
 * Pattern: Protected Shared State
 *
 * Example: A simple Counter example.
 */
@ThreadSafe
public class ProtectedSharedStateDemo {

    @GuardedBy("this")
    private int value;

    public synchronized int actualValue() {
        return value;
    }

    public synchronized void increase() {
        value++;
    }

    public synchronized void decrease() {
        value--;
    }

    public static void main(String[] args) {
        var counter = new ProtectedSharedStateDemo();
        var threadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 50; i++) {
            System.out.println("value " + counter.actualValue() + " i " + i);
            threadPool.execute(() -> counter.increase());
        }
        threadPool.shutdown();
        System.out.println(counter.actualValue());
    }
}