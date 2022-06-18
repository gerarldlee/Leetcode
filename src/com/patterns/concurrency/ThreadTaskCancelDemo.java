package com.patterns.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Pattern: Thread Task Cancel
 *
 * Motivations: Tasks that offers a cancel option are common in programming.
 * Background tasks, implemented by threads, are common in Java too and often
 * they need to be canceled.
 *
 * Intent: Show how to create a cancel mechanism using threads. Inside the
 * Runnable code, we use Thread.currentThread().isInterrupted() to identify the
 * thread state and to keep it running or not.
 *
 * Applicability: When background tasks needs a cancel option.
 *
 */
class ThreadTaskCancel {

    private Thread thread;
    private Runnable task = () -> {
        while (!Thread.currentThread().isInterrupted()) {
            // keep going - be aware of using this Pattern with the Interrupted exception! It won't work.
        }
    };

    public void run() {
        thread = new Thread(task);
        thread.start();
    }

    public void cancel() {
        if (thread != null) {
            thread.interrupt();
        }
    }

}

/**
 * Pattern: Thread Task Cancel
 *
 * Example: Canceling a Background Timer Print Task.
 *
 */
public class ThreadTaskCancelDemo {

    private Thread thread;
    private Runnable task = () -> {
        while (!Thread.currentThread().isInterrupted()) {
            var date = new Date(System.currentTimeMillis());
            System.out.println(new SimpleDateFormat().format(date));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // no need to interrupt() if you don't have anything throwing InterruptedException
                thread.interrupt();
            }
        }
    };

    public void run() {
        thread = new Thread(task);
        thread.start();
    }

    public void cancel() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        var self = new ThreadTaskCancelDemo();
        self.run();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        self.cancel();
    }
}
