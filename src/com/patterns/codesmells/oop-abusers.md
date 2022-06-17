# Object-Orientation Abusers
All these smells are incomplete or incorrect application of object-oriented programming principles.

## Switch Statements
### Signs and Symptoms
You have a complex switch operator or sequence of if statements.

### Treatment
- To isolate `switch` and put it in the right class, you may need Extract Method and then Move Method.
- If a `switch` is based on type code, such as when the program’s runtime mode is switched, use Replace Type Code with Subclasses or Replace Type Code with State/Strategy.
- After specifying the inheritance structure, use Replace Conditional with Polymorphism.

- If there are not too many conditions in the operator and they all call same method with different parameters, polymorphism will be superfluous. If this case, you can break that method into multiple smaller methods with Replace Parameter with Explicit Methods and change the `switch` accordingly.
- If one of the conditional options is null, use Introduce Null Object.

## Temporary Field
### Signs and Symptoms
Temporary fields get their values (and thus are needed by objects) only under certain circumstances. Outside of these circumstances, they are empty.

### Treatment
- Temporary fields and all code operating on them can be put in a separate class via Extract Class. In other words, you are creating a method object, achieving the same result as if you would perform Replace Method with Method Object.

- Introduce Null Object and integrate it in place of the conditional code which was used to check the temporary field values for existence.

## Refused Bequest
### Signs and Symptoms
If a subclass uses only some of the methods and properties inherited from its parents, the hierarchy is off-kilter. The unneeded methods may simply go unused or be redefined and give off exceptions.

### Treatment
- If inheritance makes no sense and the subclass really does have nothing in common with the superclass, eliminate inheritance in favor of Replace Inheritance with Delegation.

- If inheritance is appropriate, get rid of unneeded fields and methods in the subclass. Extract all fields and methods needed by the subclass from the parent class, put them in a new subclass, and set both classes to inherit from it (Extract Superclass).


## Alternative Classes with Different Interfaces

### Signs and Symptoms
Two classes perform identical functions but have different method names.

### Treatment
- Try to put the interface of classes in terms of a common denominator:

- Rename Methods to make them identical in all alternative classes.

- Move Method, Add Parameter and Parameterize Method to make the signature and implementation of methods the same.
- If only part of the functionality of the classes is duplicated, try using Extract Superclass. In this case, the existing classes will become subclasses.
- After you have determined which treatment method to use and implemented it, you may be able to delete one of the classes.
