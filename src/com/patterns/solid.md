The foundation of these design patterns and proper OOP are the SOLID principles

### Single responsibility

A class should have one and only one reason to change; should have only one job

```java
public class Vehicle {  // has 3 responsibilities:
    public void printDetails() {}   // reporting
    public double calculateValue() {}   // calculations
    public void addVehicleToDB() {}     // database
}
```


### Open-close principle

Objects or entities should be open for extension but closed for modification. This means that a class should be extendable without modifying the class itself.

```java
public class Vehicle {
    public double calculateValue() {...}
}
public class Car extends Vehicle {
    public double calculateValue() {
        return this.getValue() * 0.8;
}
public class Truck extends Vehicle{
    public double calculateValue() {
        return this.getValue() * 0.9;
}
```

### Liskov substitution

Every subclass or derived class should be substitutable for their base or parent class.

Violation:
```java
public interface Bird {
    fly();
    walk();
}
public class Dove extends Bird {
    // dove can fly
}
public class Penguin extends Bird {
    // penguins cant fly
}
```

Fix:
```java
public interface Bird {
    walk();
}
public interface FlyingBird extends Bird {
    fly();
}
public class Dove extends FlyingBird {
    // dove can fly
}
public class Penguin extends Bird {
    // penguins cant fly
}
```

### Interface segregation

A client should never be forced to implement an interface that it does not use, or clients should not be forced to depend on methods they do not use.

```java
public interface Vehicle {
    public void drive();
    public void stop();
    public void refuel();
    public void openDoors();
}
public class Bike implements Vehicle {

    // Can be implemented
    public void drive() {...}
    public void stop() {...}
    public void refuel() {...}
    
    // Can not be implemented
    public void openDoors() {...}
}
```

### Dependency inversion

Entities must depend on abstractions, not on concrete implementations. The high-level module must not depend on the low-level module. They should depend on abstractions instead.

```java
public class Car {
    private Engine engine;
    public Car(Engine e) {
        engine = e;
    }
    public void start() {
        engine.start();
    }
}
public class Engine {
   public void start() {...}
}
```

Change `Engine` to interface, so we can implement an `Engine` and connect it.

```java
public interface Engine {
    public void start();
}

public class Car {
    private Engine engine;
    public Car(Engine e) {
        engine = e;
    }
    public void start() {
        engine.start();
    }
}
public class PetrolEngine implements Engine {
    public void start() {...}
}
public class DieselEngine implements Engine {
    public void start() {...}
}
```

