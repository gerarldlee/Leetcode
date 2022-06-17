How are they different?

They are all `wrapper` patterns.  All may use composition, wrapping subject and delegating the execution to the subject at some point, do mapping one method call to another one. They spare client the necessity of having to construct a different object and copy over all relevant data. 

Saves memory and processor.

Promotes loose coupling, and makes code less exposed to inevitable changes and better readable for fellow developers.

### Proxy

Could be used when you want to lazy instantiate an object or hide the fact that you are calling a remote service, or control access to the object

Examples:
1. Remote proxy - subject is on remote server. Proxy translates method calls into RMI, REST, SOAP calls
2. Lazy Load proxy - fully initialize an object on first use
3. Access proxy - control access to subject.

### Decorator aka "Smart Proxy"

used when you want to add functionality to an object, but not by extending that object's type. This allows you to do so at runtime.

Decorator is composition.

`Proxy` and `Decorator` both have the same interface as their wrapped types, but the proxy creates an instance under the hood, whereas the decorator takes an instance in the constructor.

Examples:
1. Java IO - `BufferedOutputStream`, `FilterOutputStream` are decorators of `OutputStream`

### Adapter

used when you have an abstract interface, and you want to map that interface to another object which has similar functional role, but a different interface.

Example:
1. JAXB Marshalling adapter - maps simple flat class to more complex structure required externally and to prevent "polluting" subject class with excessive annotations.
2. `java.io.InputStreamReader` (`InputStream` returns a `Reader`)

### Bridge

very similar to Adapter, but we call it Bridge when you define both the abstract interface and the underlying implementation. i.e. you are not adapting to some legacy or third-party code, you're the designer of all the code, but you need to be able to swap out different implementations.

`Adapter` and `Bridge` both point at an existing type. But the bridge will point at an abstract type, and the adapter might point to a concrete type. The bridge will allow you to pair the implementation at runtime, whereas the adapter usually won't.

Example:
1. Collection classes in `java.util.List` implemented by `ArrayList`

### Facade

is a higher-level or simpler interface to a subsystem of one or more classes. Suppose you have a complex concept that requires multiple objects to represent. Making changes to that set of objects is confusing, because you don't always know which object has the method you need to call.

That's the time to write a Facade that provides high-level methods for all the complex operations you can do to the collection of objects.

Example: a domain model for a school section, with methods like `countStudents()`, `reportAttendance()`, etc.

`Adapter` and `Facade` both have a different interface than what they wrap. But the adapter derives from an existing interface, whereas the facade creates a new interface.