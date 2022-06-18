package com.patterns.concurrency;

import java.math.BigInteger;

/**
 * Pattern: Fixed Lock Ordering
 *
 * Motivations: Acquiring locks in a non fixed-order can deadlock if they're called at the
 * same time, but with the inverse order.
 *
 * Intent: Create a fixed-ordered locking mechanism to prevent possibles
 * deadlocks. We define a value to the locks objects and use comparisons to
 * establish a fixed order bases on who is greater or lesser.
 *
 * Applicability: Every time when acquiring more than one lock.
 *
 */
class FixedLockOrdering {

    static class LockableObject {
        private int id;
        private String anotherValue;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAnotherValue() {
            return anotherValue;
        }

        public void setAnotherValue(String anotherValue) {
            this.anotherValue = anotherValue;
        }

    }

    public void doSomeOperation(LockableObject obj1, LockableObject obj2) {
        var obj1Id = obj1.getId();
        var obj2Id = obj2.getId();
        if (obj1Id < obj2Id) {
            synchronized (obj1) {
                synchronized (obj2) {
                    // action
                }
            }
        } else {
            synchronized (obj2) {
                synchronized (obj1) {
                    // action
                }
            }
        }
    }

}
/**
 * Pattern: Fixed Lock Ordering
 *
 * Example: Simulating a coin transfer between players
 */
public class FixedLockOrderingDemo {

    static class Player {
        private int id;
        private String name;
        private BigInteger coins;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigInteger getCoins() {
            return coins;
        }

        public void depositCoins(BigInteger amount) {
            if (amount.intValue() < -1)
                throw new IllegalArgumentException("Amount can't be negative");
            this.coins = this.coins.add(amount);
        }

        public void withdrawCoins(BigInteger amount) {
            if (amount.intValue() < -1)
                throw new IllegalArgumentException("Amount can't be negative");
            this.coins = this.coins.subtract(amount);
        }
    }

    public void transferBetweenPlayers(Player playerFrom, Player playerTo, BigInteger amount) {
        var from = playerFrom.getId();
        var to = playerTo.getId();
        if (from < to) {
            synchronized (playerFrom) {
                synchronized (playerTo) {
                    transferLogic(playerFrom, playerTo, amount);
                }
            }
        } else {
            synchronized (playerTo) {
                synchronized (playerFrom) {
                    transferLogic(playerFrom, playerTo, amount);
                }
            }
        }
    }

    public void transferLogic(Player playerFrom, Player playerTo, BigInteger amount) {
        playerFrom.withdrawCoins(amount);
        playerTo.depositCoins(amount);
    }
}
