# Atomic

A transaciton is viewed as a whole set of transactions, or rolled back.

How to achieve?
- Stored procedures
- Series of queries, updates, deletes, inserts under uncommitted state, only committing it after all of the transactions are done
- Rolls back when any one transcation is not successful


# Consistent

Data has to be consistent via referential integrity.

How to achieve?
- Referential checks
- Foreign keys
- Triggers

# Isolated

In times of concurrent multi-user transactions, a data has to be updated once a data has been written

Problems:
1. Dirty Reads 
   1.1 when Transaction A updates, parallel Transaction B does not get the update
   1.2 when Transaction A rolls back, parallel Transaction B does not get the update
2. Non-repeatable read
   2.1 when Transaction A queries, but parallel Transaction B updates, Transaction A does not get the updated value  
3. Phantom Read
   3.1  when Transaction A queries a range result (where price > 2000), but Transaction B inserts a row within the range, Transaction A needs to be informed 

How to achieve?
- Set transaction isolation / pessimistic locking levels:
  1. TRANSACTION_READ_UNCOMMITTED - solves Dirty read problem 1.1, by making Transaction B read uncommitted changes
  2. TRANSACTION_READ_COMMITTED - solves Dirty read problem 1.2, by making Transaction B read transactions only when committed
  3. TRANSACTION_REPEATABLE_READ - solves Non-repeatable read problem, by making Transaction A repeat the read query for the update
  4. TRANSACTION_SERIALIZABLE - the most isolated of them all. solves Phantom Read problem. No 2 transactions can access the same set of data. Performance problem 

- Optimistic locking (not really a locking)
  1. In a transaction, we will have a Version number, and or Timestamp or both. Everytime it reads data, it has them. Everytime it updates the data, it will make sure they are both same or latest, if not, it will discard the transaciton.

# Durable

When a transaction is committed, it has to be written on disk in case of failure

How to achieve?
- DB vendor specific
- Automatic


# Distributed ACID patterns

1. 2 phase commit - update resources on multiple nodes in one atomic operation
   https://martinfowler.com/articles/patterns-of-distributed-systems/two-phase-commit.html