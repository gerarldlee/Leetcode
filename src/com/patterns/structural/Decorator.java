package com.patterns.structural;

/**
 * Motivation
 * Attach additional behaviors / responsibilities to an object dynamically by placing these objects inside special wrapper that contain the behaviors.
 * Decorators provide a flexible alternative to subclassing for extending functionality. Composition.
 * Client-specific embellishment of a core object by recursively wrapping it
 * Wrapping a gift, putting it in a box and wrapping the box
 *
 * References:
 * https://refactoring.guru/design-patterns/decorator
 * https://sourcemaking.com/design_patterns/decorator
 *
 */
// 1. "lowest common denominator"
interface Widget {
    void draw();
}

// 3. "Core" class with "is a" relationship
class TextField implements Widget {
    private int width, height;

    public TextField(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void draw() {
        System.out.println("TextField: " + width + ", " + height);
    }
}

// 2. Second level base class with "isa" relationship
abstract class WidgetDecorator implements Widget {
    // 4. "has a" relationship
    private Widget widget;

    public WidgetDecorator(Widget widget) {
        this.widget = widget;
    }

    // 5. Delegation
    public void draw() {
        widget.draw();
    }
}

// 6. Optional embellishment
class BorderWidgetDecorator extends WidgetDecorator {
    public BorderWidgetDecorator(Widget widget) {
        super(widget);
    }
    public void draw() {
        // 7. Delegate to base class and add extra stuff
        super.draw();
        System.out.println("  BorderDecorator");
    }
}

// 6. Optional embellishment
class ScrollWidgetDecorator extends WidgetDecorator {
    public ScrollWidgetDecorator(Widget widget) {
        super(widget);
    }
    public void draw() {
        super.draw(); // 7. Delegate to base class and add extra stuff
        System.out.println("  ScrollDecorator");
    }
}

public class Decorator {
    public static void main(String[] args) {
        // 8. Client has the responsibility to compose desired configurations
        Widget widget = new BorderWidgetDecorator(new BorderWidgetDecorator(new ScrollWidgetDecorator(new TextField(80, 24))));
        widget.draw();
    }
}
