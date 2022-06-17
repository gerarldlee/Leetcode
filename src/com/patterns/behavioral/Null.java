package com.patterns.behavioral;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Motivation
 * Encapsulates the absence of an object by providing a substitutable alternative that offers suitable default do nothing behavior.
 * In short, a design where "nothing will come from nothing"
 *
 * Use when:
 * - an object requires a collaborator. The null object pattern does not introduce this collaboration - it makes use of a collaboration that already exists
 * - some collaborator instances should do nothing
 * - you want to abstract the handling of null away from the client
 *
 * References
 * https://sourcemaking.com/design_patterns/null_object
 */
class NullOutputStream extends OutputStream {
    public void write(int b) {
        // Do nothing
    }
}

class NullPrintStream extends PrintStream {
    public NullPrintStream() {
        super(new NullOutputStream());
    }
}

class Application {
    private PrintStream debugOut;
    public Application(PrintStream debugOut) {
        this.debugOut = debugOut;
    }

    public void doSomething() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
            debugOut.println("i = " + i);
        }
        System.out.println("sum = " + sum);
    }
}

public class Null {
    public static void main(String[] args) {
        Application app = new Application(new NullPrintStream());
        app.doSomething();
    }
}
