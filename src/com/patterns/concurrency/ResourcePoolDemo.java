package com.patterns.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Pattern: Resource Pool
 *
 * Motivations: Some resources can be limited, and it's import to ensures those
 * limits in concurrent programming.
 *
 * Intent: Establish a mechanism for limiting the resource use. It'll block the
 * user when there's none available. It Implements the concurrent thread-safe
 * pool using Semaphores.
 *
 * Applicability: Use when you want to create a pool of some limited resource.
 *
 */
class ResourcePool<T> {

    private final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private Semaphore semaphore;
    private BlockingQueue<T> resources;

    public ResourcePool(int poolSize, List<T> initializedResources) {
        this.semaphore = new Semaphore(poolSize, true);
        this.resources = new LinkedBlockingQueue<>(poolSize);
        this.resources.addAll(initializedResources);
    }

    public T get() throws InterruptedException {
        return get(Integer.MAX_VALUE);
    }

    public T get(long secondsToTimeout) throws InterruptedException {
        semaphore.acquire();
        try {
            T resource = resources.poll(secondsToTimeout, TIME_UNIT);
            return resource;
        } finally {
            semaphore.release();
        }
    }

    public void release(T resource) throws InterruptedException {
        if (resource != null) {
            resources.put(resource);
            semaphore.release();
        }
    }

}

/**
 * Pattern: Resource Pool
 *
 * Example: Resource Pool usage
 */
public class ResourcePoolDemo {
    public static void main(String[] args) {
        var executor = Executors.newCachedThreadPool();
        var pool = new ResourcePool<Integer>(15,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 14));
        var random = new Random();
        for (int i = 0; i < 30; i++) {
            executor.execute(() -> {
                try {
                    var value = pool.get(60);
                    System.out.println("Value taken: " + value);
                    Thread.sleep(random.nextInt(5000));
                    pool.release(value);
                    System.out.println("Value released " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
