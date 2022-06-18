package com.patterns.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Pattern: Thread Local Confinement
 *
 * Motivation: Dealing with non thread-safe objects is a common situation, and
 * it's possible to still have a thread-safe code even using non thread-safe
 * classes.
 *
 * Intent: Use ThreadLocal to confine instances in a per-thread model, keeping a
 * object copy to each thread.
 *
 * Applicability: Use for non thread-safe objects that needs to be shared across threads.
 *
 */
class ThreadLocalConfinement {

    private static final ThreadLocal<Object> threadLocalOject = new ThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            return new Object();
        }
    };

    public Object getNowThreadSafeObjectInstance() {
        return threadLocalOject.get();
    }

}

/**
 * Pattern: Thread Local Confinement
 *
 * Example: Using a thread safe SimpleDateFormat object
 *
 */
public class ThreadLocalConfinementDemo {
    private static final ThreadLocal<SimpleDateFormat> threadLocalDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        }
    };

    public String format(Date date) {
        return threadLocalDateFormat.get().format(date);
    }
}
