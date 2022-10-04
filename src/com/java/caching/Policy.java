package com.java.caching;

public interface Policy<V> {

    int evictEntryIndex(Entry<V>[] entries, int startIndex, int endIndex);
}