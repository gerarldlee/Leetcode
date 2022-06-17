# Couplers
All the smells in this group contribute to excessive coupling between classes or show what happens if coupling is replaced by excessive delegation.

## Feature Envy

### Signs and Symptoms
A method accesses the data of another object more than its own data.

### Treatment
As a basic rule, if things change at the same time, you should keep them in the same place. Usually data and functions that use this data are changed together (although exceptions are possible).

- If a method clearly should be moved to another place, use Move Method.

- If only part of a method accesses the data of another object, use Extract Method to move the part in question.

- If a method uses functions from several other classes, first determine which class contains most of the data used. Then place the method in this class along with the other data. Alternatively, use Extract Method to split the method into several parts that can be placed in different places in different classes.


## Inappropriate Intimacy

### Signs and Symptoms

One class uses the internal fields and methods of another class.

### Treatment
The simplest solution is to use Move Method and Move Field to move parts of one class to the class in which those parts are used. But this works only if the first class truly does not need these parts.

- Another solution is to use Extract Class and Hide Delegate on the class to make the code relations “official”.
- If the classes are mutually interdependent, you should use Change Bidirectional Association to Unidirectional.

- If this “intimacy” is between a subclass and the superclass, consider Replace Delegation with Inheritance.

## Message Chains

### Signs and Symptoms
In code you see a series of calls resembling `$a->b()->c()->d()`

### Treatment
To delete a message chain, use Hide Delegate.

Sometimes it is better to think of why the end object is being used. Perhaps it would make sense to use Extract Method for this functionality and move it to the beginning of the chain, by using Move Method.

## Middle Man
### Signs and Symptoms
If a class performs only one action, delegating work to another class, why does it exist at all?

### Treatment
If most of a method’s classes delegate to another class, Remove Middle Man is in order.
