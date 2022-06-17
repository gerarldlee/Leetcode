# Bloaters

Bloaters are code, methods and classes that have increased to such gargantuan proportions that they are hard to work with. Usually these smells do not crop up right away, rather they accumulate over time as the program evolves (and especially when nobody makes an effort to eradicate them).

## Long Method

### Signs and Symptoms
A method contains too many lines of code. Generally, any method longer than ten lines should make you start asking questions.

### Treatment
As a rule of thumb, if you feel the need to comment on something inside a method, you should take this code and put it in a new method. Even a single line can and should be split off into a separate method, if it requires explanations. And if the method has a descriptive name, nobody will need to look at the code to see what it does.
- To reduce the length of a method body, use Extract Method.
- If local variables and parameters interfere with extracting a method, use Replace Temp with Query, Introduce Parameter Object or Preserve Whole Object.
- If none of the previous recipes help, try moving the entire method to a separate object via Replace Method with Method Object.
- Conditional operators and loops are a good clue that code can be moved to a separate method. For conditionals, use Decompose Conditional. If loops are in the way, try Extract Method.

## Large Class

### Signs and Symptoms
A class contains many fields/methods/lines of code.

### Treatment
When a class is wearing too many (functional) hats, think about splitting it up:

- Extract Class helps if part of the behavior of the large class can be spun off into a separate component.

- Extract Subclass helps if part of the behavior of the large class can be implemented in different ways or is used in rare cases.

- Extract Interface helps if it is necessary to have a list of the operations and behaviors that the client can use.

- If a large class is responsible for the graphical interface, you may try to move some of its data and behavior to a separate domain object. In doing so, it may be necessary to store copies of some data in two places and keep the data consistent. Duplicate Observed Data offers a way to do this.

## Primitive Obsession
### Signs and Symptoms
- Use of primitives instead of small objects for simple tasks (such as currency, ranges, special strings for phone numbers, etc.)

- Use of constants for coding information (such as a constant USER_ADMIN_ROLE = 1 for referring to users with administrator rights.)

- Use of string constants as field names for use in data arrays.

### Treatment
If you have a large variety of primitive fields, it may be possible to logically group some of them into their own class. Even better, move the behavior associated with this data into the class too. For this task, try Replace Data Value with Object.

- If the values of primitive fields are used in method parameters, go with Introduce Parameter Object or Preserve Whole Object.
- When complicated data is coded in variables, use Replace Type Code with Class, Replace Type Code with Subclasses or Replace Type Code with State/Strategy.
- If there are arrays among the variables, use Replace Array with Object.


## Long Parameter List
### Signs and Symptoms
More than three or four parameters for a method.

### Treatment
- Check what values are passed to parameters. If some of the arguments are just results of method calls of another object, use Replace Parameter with Method Call. This object can be placed in the field of its own class or passed as a method parameter.

- Instead of passing a group of data received from another object as parameters, pass the object itself to the method, by using Preserve Whole Object.

- If there are several unrelated data elements, sometimes you can merge them into a single parameter object via Introduce Parameter Object.

## Data Clumps
### Signs and Symptoms
Sometimes different parts of the code contain identical groups of variables (such as parameters for connecting to a database). These clumps should be turned into their own classes.

### Treatment
- If repeating data comprises the fields of a class, use Extract Class to move the fields to their own class.

- If the same data clumps are passed in the parameters of methods, use Introduce Parameter Object to set them off as a class.

- If some of the data is passed to other methods, think about passing the entire data object to the method instead of just individual fields. Preserve Whole Object will help with this.

- Look at the code used by these fields. It may be a good idea to move this code to a data class.

