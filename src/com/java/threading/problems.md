# Problems in Concurrent programming

Problems with sharing memory:
- Mutual Exclusion. Threads need to have exclusive access to shared state or memory to ensure correctness.
  - Ways:
    - synchronization of shared resources
    - lock
    - monitor
    - semaphore
    - mutex
  - Problems:
    - error prone and often leads to performance bottlenecks
    - can lead to deadlock & livelock
- Context switching (OS processes / applications and threads inside them).
  - Ways:
    - This is OS managed, so no problem. ie. time-slicing / epoch
  - Problems:
    - When threads are frequently switched between different states, their current states needs to be saved and resumed.
    - time-consuming

# How concurrent modules (threads or processes) communicate?

1. Shared memory
2. Message passing

# Design patterns for concurrency

## Actor based concurrency
Actor model. Mathematical model of concurrent computation that basically treats everyting as an actor.

Actors can pass messages to each other and in response to a message, can make local decisioons.

Example: Scala

```java
class myActor extends Actor {
    def act() {
        while(true) {
            receive {
                // Perform some action
            }
        }
    }
}
```

In the example above, a call to the receive method inside an infinite loop suspends the actor until a message arrives. Upon arrival, the message is removed from the actor's mailbox, and the necessary actions are taken.

The actor model eliminates one of the fundamental problems with concurrent programming — shared memory. Actors communicate through messages, and each actor processes messages from its exclusive mailboxes sequentially. However, we execute actors over a thread pool. And we've seen that native threads can be heavyweight and, hence, limited in number.

## Event based concurrency

Designed to address the problem of threads are costly to spawn and operate.  

Event loop blocks on the event provider and dispatches an event to an event handler on arrival.

```java
while(true) {
    events = getEvents();
    for(e in events)
        processEvent(e);
}
```

This design eliminates deadlock.

Example: Javascript implements event loop to offer async programming.

## Non-blocking algorithms

Suspension of one thread does not lead to suspension of another. This will increase our responsiveness & performance significantly.

This is the opposite of locking or mutual exclusion.

Makes use of compare-and-swap atomic primitive that is provided by the underlying hardware. Hardware will compare the contents of a memory location with a given value, and only if they are the same, will it update the value to a new given value.

Implementation of non-blocking data structure in Java:
- `AtomicBoolean`, `AtomicInteger`, `AtomicLong`, `AtomicReference`

Consider non-thread safe:
```java
boolean open = false;
if(!open) {
    // Do Something
    open=false;
}
```

Solution: synchronize the piece of code with a lock, or use an atomic operation:

```java
AtomicBoolean open = new AtomicBoolean(false);
if(open.compareAndSet(false, true) {
    // Do Something
}
```

# Concurrency in other Programming languages

## Goroutines in Go

Light-weight threads. Functions or methods that can run concurrently with other functions or methods. They are extremely cheap as they occupy few kilobytes in stack memory

They communicate each other using channels, and avoids access to shared memory.

## Processes in Erlang

Each thread execution is a process. Not an OS process. But are light-weight with a small memory footprint and are fast to create and dispose of, with low scheduling overhead.

Under the hood, they are functions that the erlang-runtime handles scheduling for.  They do not share any data, and communicates with each other by message passing.

## Project Loom (Java Fibers)

Green-like thread abstraction that can pause and continue concurrency, essentially allowing us to manage threads like an OS.

# Frameworks

## Application layer

1. Akka - toolkit written in Scala for building highly concurrent and distributed applications on the JVM. Based on the Actor model.
2. Reactor - reactive library for building non-blocking applications. Focuses on efficient message passing and demand management (backpressure). High-throughput messages
3. Netty - async event driven network application framework. Allows to develop highly concurrent protocol servers and clients. Leverages NIO. 
   1. Advantages: Better throughput, lower latency, less resources' consumption, minimizes unnecessary memory copy

## Data layer

1. Cassandra - provides high-availability, scalability, fault tolerance on commodity hardware. Does not provide ACID transaction.
2. Kafka - distributed streaming platform. Stores stream of records in topics. Provides linear horizontal scaling for both producers and consunmers of records while at the same time, providing high reliability and durability.
   1. Partitions, replicas, and brokers are some of the fundamental concepts on which it provides massively-distributed concurrency.

## Cache layer

1. Hazelcast - distributed cloud-friendly, in-memory object store, and compute engine that supports wide variety of data structures i.e. Map, Set, List, MultiMap, RingBuffer and HyperLogLog
   1. Built in replication and offer high availability and automatic partitioning
2. Redis - in-memory data structure store that we primarily use as cache. in-memory key value. Can be networked.