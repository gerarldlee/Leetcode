package com.patterns.concurrency;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Pattern: Lock Split
 *
 * Motivations: If you have shared, mutable, independent and hot variables, you
 * can increase performance by giving each variable or variable group it's own
 * lock.
 *
 * Intent: If the variables or variables groups are independent in terms of
 * logic and usage, we guard their state by assigning a lock to which one of
 * then. We protect all paths that interacts with each variable or variable
 * group, creating a thread safe class that is efficiently in term of lock
 * contention and other hazards like race conditions.
 *
 * Applicability: Classes where you have shared, mutable, independent and hot
 * variables or variables groups, where one single lock will be inefficiently.
 *
 */
@ThreadSafe
class LockSplit {

    @GuardedBy("lockState")
    private List<Object> hotState;

    @GuardedBy("lockAnotherState")
    private Object anotherState;

    @GuardedBy("lockOtherState")
    private Object otherState;

    private Lock lockState = new ReentrantLock();
    private Lock lockOtherStates = new ReentrantLock();

    public List<Object> stateReader() {
        lockState.lock();
        try {
            return hotState;
        } finally {
            lockState.unlock();
        }
    }

    public void stateWriterMethod(Object param) {
        lockState.lock();
        try {
            this.hotState.add(param);
        } finally {
            lockState.unlock();
        }
    }

    public Object anotherStateReader() {
        lockOtherStates.lock();
        try {
            return anotherState;
        } finally {
            lockOtherStates.unlock();
        }
    }

    public void anotherStateWriterMethod(Object param) {
        lockOtherStates.lock();
        try {
            this.anotherState = param;
        } finally {
            lockOtherStates.unlock();
        }
    }

    public Object otherStateReader() {
        lockOtherStates.lock();
        try {
            return otherState;
        } finally {
            lockOtherStates.unlock();
        }
    }

    public void otherStateWriterMethod(Object param) {
        lockOtherStates.lock();
        try {
            this.otherState = param;
        } finally {
            lockOtherStates.unlock();
        }
    }

}

/**
 * Pattern: Lock Split
 *
 * Example A shared OS tool and command configuration example. A class that will
 * be shared through several threads to be filled.
 *
 */
@ThreadSafe
public class LockSplitDemo {

    @GuardedBy("lockExecutorCommands")
    private List<String> executorCommands;

    @GuardedBy("lockToolName")
    private String toolName;

    @GuardedBy("lockUser")
    private String user;

    @GuardedBy("lockPassword")
    private String password;

    private Lock lockExecutorCommands = new ReentrantLock();
    private Lock lockToolName = new ReentrantLock();
    private Lock lockUser = new ReentrantLock();
    private Lock lockPassword = new ReentrantLock();

    public List<String> getExecutorCommands() {
        lockExecutorCommands.lock();
        try {
            return executorCommands;
        } finally {
            lockExecutorCommands.unlock();
        }
    }

    public void addExecutorCommands(String executorCommand) {
        lockExecutorCommands.lock();
        try {
            this.executorCommands.add(executorCommand);
        } finally {
            lockExecutorCommands.unlock();
        }
    }

    public void removeExecutorCommands(String executorCommand) {
        lockExecutorCommands.lock();
        try {
            this.executorCommands.remove(executorCommand);
        } finally {
            lockExecutorCommands.unlock();
        }
    }

    public String getToolName() {
        lockToolName.lock();
        try {
            return toolName;
        } finally {
            lockToolName.unlock();
        }
    }

    public void setToolName(String toolName) {
        lockToolName.lock();
        try {
            this.toolName = toolName;
        } finally {
            lockToolName.unlock();
        }
    }

    public String getUser() {
        lockUser.lock();
        try {
            return user;
        } finally {
            lockUser.unlock();
        }
    }

    public void setUser(String user) {
        lockUser.lock();
        try {
            this.user = user;
        } finally {
            lockUser.unlock();
        }
    }

    public String getPassword() {
        lockPassword.lock();
        try {
            return password;
        } finally {
            lockPassword.unlock();
        }
    }

    public void setPassword(String password) {
        lockPassword.lock();
        try {
            this.password = password;
        } finally {
            lockPassword.unlock();
        }
    }
}
