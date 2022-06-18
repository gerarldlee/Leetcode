package com.patterns.concurrency;

import java.util.UUID;

/**
 * Pattern: Immutable Object with Volatile Reference
 *
 * Motivations: Mutable state is troublesome in concurrent environments for
 * being vulnerable to visibility and atomic related hazards, like inconsistent
 * state objects, lost updates, stale values and others. An Immutable objects is
 * one whose status does not mutate after constructed, and so is thread-safe.
 * IMPORTANT: references to immutable objects are not thread-safe.
 *
 * Intent: Make all fields final and don't use any method that can mutate the
 * internal state of the object; don't expose the object until it's full
 * created; make the class final, prohibiting extension. It's important to refer
 * to an immutable object using a volatile field, because it has visibility
 * guarantees (every change to the immutable object variable will be made
 * visible to all threads), and any synchronizing mechanism to ensure
 * thread-safety for the reference.
 *
 * Applicability: When sharing a object, use Immutable Objects being referenced
 * by a volatile fields whatever it's possible.
 *
 */
class ImmutableObject {

    private volatile MyImmutableObject safeReference = new MyImmutableObject(0, "", false);

    public void updateField(int id, String newValue, boolean newAnotherValue) {
        this.safeReference = new MyImmutableObject(id, newValue, newAnotherValue);
    }

    public void printMyImmutableObject() {
        System.out.println(safeReference);
    }

    public final static class MyImmutableObject {
        private final int id;
        private final String aValue;
        private final boolean anotherValue;

        public MyImmutableObject(int id, String aValue, boolean anotherValue) {
            super();
            this.id = id;
            this.aValue = aValue;
            this.anotherValue = anotherValue;
        }

        public int getId() {
            return id;
        }

        public String getaValue() {
            return aValue;
        }

        public boolean getAnotherValue() {
            return anotherValue;
        }

        @Override
        public String toString() {
            return "MyImmutableObject [id=" + id + ", aValue=" + aValue + ", anotherValue=" + anotherValue + "]";
        }

    }

}

/**
 * Pattern: Immutable Object with Volatile Reference
 *
 * Example: Event keeper
 */
public class ImmutableObjectDemo {
    private volatile Event lastEvent = new Event(null, null, null, null, null);

    public void acceptEvent(String name, String type, String username, byte[] payload) {
        switch (type) {
            case "STORAGE":
                lastEvent = new Event(UUID.randomUUID().toString(), name, type, username, payload);
                break;
            default:
                break;
        }
    }

    public Event getLastEvent() {
        return lastEvent;
    }

    static final class Event {
        private final String id;
        private final String name;
        private final String type;
        private final String username;
        private final byte[] payload;

        public Event(String id, String name, String type, String username, byte[] payload) {
            super();
            this.id = id;
            this.name = name;
            this.type = type;
            this.username = username;
            this.payload = payload;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getUsername() {
            return username;
        }

        public byte[] getPayload() {
            return payload;
        }

    }
}
