package com.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Motivation
 * Lets you reduce chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object
 *
 * Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently
 * Design an intermediary to decouple many peers
 * Promote the many-to-many relationships between interacting peers to a "full object status"
 *
 * References:
 *
 * https://refactoring.guru/design-patterns/mediator
 */
// 1. The "intermediary"
class IntermediaryMediator {
    // 4. The Mediator arbitrates
    private boolean slotFull = false;
    private int number;

    public synchronized void storeMessage(int num) {
        // no room for another message
        while (slotFull == true) {
            try {
                wait();
            }
            catch (InterruptedException e ) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = true;
        number = num;
        notifyAll();
    }

    public synchronized int retrieveMessage() {
        // no message to retrieve
        while (slotFull == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = false;
        notifyAll();
        return number;
    }
}

class Producer implements Runnable {
    // 2. Producers are coupled only to the Mediator
    private IntermediaryMediator med;
    private int id;
    private static int num = 1;

    public Producer(IntermediaryMediator m) {
        med = m;
        id = num++;
    }

    @Override
    public void run() {
        int num;
        while (true) {
            med.storeMessage(num = (int)(Math.random()*100));
            System.out.print( "p" + id + "-" + num + "  " );
        }
    }
}

class Consumer implements Runnable {
    // 3. Consumers are coupled only to the Mediator
    private IntermediaryMediator med;
    private int    id;
    private static int num = 1;

    public Consumer(IntermediaryMediator m) {
        med = m;
        id = num++;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
        }
    }
}

public class Mediator {
    public static void main( String[] args ) {
        List<Thread> producerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ENTER for exit");
        IntermediaryMediator mb = new IntermediaryMediator();
        producerList.add(new Thread(new Producer(mb)));
        producerList.add(new Thread(new Producer(mb)));
        producerList.add(new Thread(new Consumer(mb)));
        producerList.add(new Thread(new Consumer(mb)));
        producerList.add(new Thread(new Consumer(mb)));
        producerList.add(new Thread(new Consumer(mb)));
        for (Thread p : producerList) {
            p.start();
        }
        boolean stop = false;
        String exit = scanner.nextLine();
        while (!stop) {
            if (exit.equals("")) {
                stop = true;
                for (Thread p : producerList) {
                    //noinspection deprecation
                    p.stop();
                }
            }
        }
    }
}
