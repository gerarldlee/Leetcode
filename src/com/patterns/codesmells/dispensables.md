# Dispensables
A dispensable is something pointless and unneeded whose absence would make the code cleaner, more efficient and easier to understand.

## Comments

### Signs and Symptoms
A method is filled with explanatory comments.

### Treatment
- If a comment is intended to explain a complex expression, the expression should be split into understandable subexpressions using Extract Variable.

- If a comment explains a section of code, this section can be turned into a separate method via Extract Method. The name of the new method can be taken from the comment text itself, most likely.

- If a method has already been extracted, but comments are still necessary to explain what the method does, give the method a self-explanatory name. Use Rename Method for this.

- If you need to assert rules about a state that is necessary for the system to work, use Introduce Assertion.

## Duplicate code

### Signs and Symptoms
Two code fragments look almost identical.

### Treatment
If the same code is found in two or more methods in the same class: use Extract Method and place calls for the new method in both places.

- If the same code is found in two subclasses of the same level:

    - Use Extract Method for both classes, followed by Pull Up Field for the fields used in the method that you are pulling up.
    - If the duplicate code is inside a constructor, use Pull Up Constructor Body.
    - If the duplicate code is similar but not completely identical, use Form Template Method.
    - If two methods do the same thing but use different algorithms, select the best algorithm and apply Substitute Algorithm.
  
- If duplicate code is found in two different classes:

    - If the classes are not part of a hierarchy, use Extract Superclass in order to create a single superclass for these classes that maintains all the previous functionality.
    - If it is difficult or impossible to create a superclass, use Extract Class in one class and use the new component in the other.
  
- If a large number of conditional expressions are present and perform the same code (differing only in their conditions), merge these operators into a single condition using Consolidate Conditional Expression and use Extract Method to place the condition in a separate method with an easy-to-understand name.
- If the same code is performed in all branches of a conditional expression: place the identical code outside of the condition tree by using Consolidate Duplicate Conditional Fragments.

## Lazy class

### Signs and Symptoms
Understanding and maintaining classes always costs time and money. So if a class doesn’t do enough to earn your attention, it should be deleted.

### Treatment
Components that are near-useless should be given the Inline Class treatment.

For subclasses with few functions, try Collapse Hierarchy.

## Data class

### Signs and Symptoms
A data class refers to a class that contains only fields and crude methods for accessing them (getters and setters). These are simply containers for data used by other classes. These classes do not contain any additional functionality and cannot independently operate on the data that they own.

### Treatment
- If a class contains public fields, use Encapsulate Field to hide them from direct access and require that access be performed via getters and setters only.

- Use Encapsulate Collection for data stored in collections (such as arrays).

- Review the client code that is used by the class. In it, you may find functionality that would be better located in the data class itself. If this is the case, use Move Method and Extract Method to migrate this functionality to the data class.

After the class has been filled with well thought-out methods, you may want to get rid of old methods for data access that give overly broad access to the class data. For this, Remove Setting Method and Hide Method may be helpful.

## Dead code

### Signs and Symptoms
A variable, parameter, field, method or class is no longer used (usually because it is obsolete).

### Treatment
The quickest way to find dead code is to use a good IDE.

Delete unused code and unneeded files.

- In the case of an unnecessary class, Inline Class or Collapse Hierarchy can be applied if a subclass or superclass is used.
- To remove unneeded parameters, use Remove Parameter.

## Speculative Generality

### Signs and Symptoms
There is an unused class, method, field or parameter.

### Treatment
For removing unused abstract classes, try Collapse Hierarchy.

- Unnecessary delegation of functionality to another class can be eliminated via Inline Class.

- Unused methods? Use Inline Method to get rid of them.

- Methods with unused parameters should be given a look with the help of Remove Parameter.

- Unused fields can be simply deleted.