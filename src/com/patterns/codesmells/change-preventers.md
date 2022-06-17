# Change Preventers
These smells mean that if you need to change something in one place in your code, you have to make many changes in other places too. Program development becomes much more complicated and expensive as a result.

## Divergent Change

### Signs and Symptoms
You find yourself having to change many unrelated methods when you make changes to a class. For example, when adding a new product type you have to change the methods for finding, displaying, and ordering products.

### Treatment
- Split up the behavior of the class via Extract Class.

- If different classes have the same behavior, you may want to combine the classes through inheritance (Extract Superclass and Extract Subclass).

## Shotgun Surgery

### Signs and Symptoms
Making any modifications requires that you make many small changes to many different classes.

### Treatment
- Use Move Method and Move Field to move existing class behaviors into a single class. If there is no class appropriate for this, create a new one.

- If moving code to the same class leaves the original classes almost empty, try to get rid of these now-redundant classes via Inline Class.

## Parallel Inheritance Hierarchies

### Signs and Symptoms
Whenever you create a subclass for a class, you find yourself needing to create a subclass for another class.

### Treatment
You may de-duplicate parallel class hierarchies in two steps. First, make instances of one hierarchy refer to instances of another hierarchy. Then, remove the hierarchy in the referred class, by using Move Method and Move Field.


