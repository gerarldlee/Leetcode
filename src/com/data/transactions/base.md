BASE is a technique within the distributed architecture. Its not like ACID where a DB vendor has to be ACID compliant.

# Basically Available

Failure will not halt the system

# Soft state

state of the system will change over time

# Eventually consistent

system will become consistent over time

## Patterns of Eventual Consistency

1. Background synchronization 
    Responsible for synchronizing all the data sources.
   1. Ways to accomplish
      1. Batch job
      2. Cron job
   2. Problems
      1. This is an anti-pattern - makes the database coupled
      2. Does not perform an operation immediately, if you want to change the record, but rather, the operation stays in the background process
         1. Breaks the bounded context of a data
         2. https://martinfowler.com/bliki/ApplicationBoundary.html

2. Request based synchronization
    Preserves bounded context in the case of microservice. 
   1. Ways to accomplish
      1. Sends request to various microservices to perform an operation
   2. Problems
      1. response times are slow
      2. data consistency is also impacted when other services broke down, while the data was committed to other services

3. Event based synchronization
    Best pattern. 
   1. Ways to accomplish
      1. publish to a topic - durable subscribers performs the operation
