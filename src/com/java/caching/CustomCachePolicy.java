package com.java.caching;

public class CustomCachePolicy<V> implements Policy<V> {

    @Override
    public int evictEntryIndex(Entry<V>[] entries, int startIndex, int endIndex) {
        return (startIndex + (endIndex - startIndex) / 2);
    }
}
