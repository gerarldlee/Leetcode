package com.patterns.behavioral;

/**
 * Motivation
 * Defines a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from the clients that use it
 * Capture the abstraction in an interface, bury implementation details in derived classes
 * Open-close principle
 *
 *
 * References:
 * https://refactoring.guru/design-patterns/strategy
 * https://sourcemaking.com/design_patterns/strategy
 */
// 1. Define the interface of the algorithm
interface StrategyIF {
    void solve();
}

// 2. Bury implementation
@SuppressWarnings("ALL")
abstract class StrategySolution implements StrategyIF {
    // 3. Template Method
    public void solve() {
        start();
        while (nextTry() && !isSolution()) {}
        stop();
    }

    abstract void start();
    abstract boolean nextTry();
    abstract boolean isSolution();
    abstract void stop();
}

class FOO extends StrategySolution {
    private int state = 1;

    protected void start() {
        System.out.print("Start  ");
    }

    protected void stop() {
        System.out.println("Stop");
    }

    protected boolean nextTry() {
        System.out.print("NextTry-" + state++ + "  ");
        return true;
    }

    protected boolean isSolution() {
        System.out.print("IsSolution-" + (state == 3) + "  ");
        return (state == 3);
    }
}

// 2. Bury implementation
abstract class StrategySearch implements StrategyIF {
    // 3. Template Method
    public void solve() {
        while (true) {
            preProcess();
            if (search()) {
                break;
            }
            postProcess();
        }
    }

    abstract void preProcess();
    abstract boolean search();
    abstract void postProcess();
}

@SuppressWarnings("ALL")
class BAR extends StrategySearch {
    private int state = 1;

    protected void preProcess()  {
        System.out.print("PreProcess  ");
    }

    protected void postProcess() {
        System.out.print("PostProcess  ");
    }

    protected boolean search() {
        System.out.print("Search-" + state++ + "  ");
        return state == 3 ? true : false;
    }
}

// 4. Clients couple strictly to the interface
public class Strategy {
    // client code here
    private static void execute(StrategyIF strategy) {
        strategy.solve();
    }

    public static void main( String[] args ) {
        StrategyIF[] algorithms = {new FOO(), new BAR()};
        for (StrategyIF algorithm : algorithms) {
            execute(algorithm);
        }
    }
}
