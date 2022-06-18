# There are generally 5 types:

## For sharing resources among multiple threads or processes:
1. Active object - decouples method execuition from method invocation. The purpose is to enhance concurrency and simplify synchronized access to objects that reside in their own threads of control.
2. Monitor object - synchronizes concurrent method execution to ensure that only one method at a time runs within an object. It also allows an object's methods to schedule their execution sequences cooperatively. 

Both can sync and schedule concurrent method. The main difference is that Active object executes its methods in a different thread than its clients, whereas Monitor object executes its methods by borrowing the thread of its clients.
- Active object can perform more sophisticated but expensive scheduling to determine the order in which their methods execute.

## Higher-level concurrency architectures:

3. Half sync/half async - architectural pattern decouples asynchronous and synchronous processing in concurrent systems, to simplify programming without reducing performance undudly. This pattern introduces two intercommunicating layers, one for asynchronous and one for synchronous service processing. A queuing layer mediates communication between services in the asynchronous and synchronous layers.
4. Leader/followers - architectural pattern provides an efficient concurrency model where multiple threads take turns to share a set of event sources to detect, demultiplex, dispatch, and process service requests that occur on the event sources. The Leader/Followers pattern can be used in lieu of the Half-Sync/Half-Async and Active Object patterns to improve performance when there are no synchronization or ordering constraints on the processing of requests by pooled threads.

Implementors of the Half-Sync/Half-Async and Leader/Followers patterns can use the Active Object and Monitor Object patterns to coordinate access to shared objects efficiently.

## Other strategy for addressing certain inherent complexity of concurrency:
5. Thread-specific storage - design pattern allows multiple threads to use one `logically global' access point to retrieve an object that is local to a thread, without incurring locking overhead on each access to the object. To some extent this pattern can be viewed as the `antithesis' of the other patterns in this section, because it addresses several inherent complexities of concurrency by preventing the sharing of resources among threads.

- Master-slave
- Producer-consumer
- Scheduler
- Two-phase termination