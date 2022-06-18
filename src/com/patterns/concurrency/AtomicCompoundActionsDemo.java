package com.patterns.concurrency;

/**
 *
 * Pattern: Atomic Compound Actions
 *
 * Motivations: Compounded actions are actions that depends on a sequence of
 * events. They need to be executed atomically as a single unit which totally
 * fails or complete. check-then-act, read-modify-write and compare-and-swap are
 * common idioms in concurrent programming that can cause race conditions if not
 * treated right.
 *
 * Intent: Prevent race condition issues while using intrinsic locking
 * mechanisms when executing compound actions; protect every path where the
 * involved variables are used.
 *
 * Applicability: read-modify-write (i++ operator), check-then-act (lazy
 * initialization, singleton), compare-and-swap (Stacks).
 *
 */
@ThreadSafe
class AtomicCompoundActions {

    @GuardedBy("this")
    private Object value;

    // example
    public synchronized void checkThenAct() {
        if (value != null) { // check
            dependentAction(); // if true then-act
        }
    }

    public void dependentAction() {
    }

    public synchronized void getValue() {

    }

}

/**
 * Example: Simple Atomic Compound Actions using Compare-and-swap idiom
 */
public class AtomicCompoundActionsDemo {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expected, int newValue) {
        int old = value;
        if (old == expected) {
            value = newValue;
        }
        return old;
    }
}
