package com.java.caching;

import com.java.caching.impl.NWayCache;

public class CacheFactory {
    public static <K,V> Cache<K,V> newCache(int associativity, int cacheSize, int blockSize, Policy<V> evictionPolicy){
        return new NWayCache<K, V>(associativity, cacheSize, blockSize, evictionPolicy);
    }

    public static <K,V> Cache<K,V>  newCache(int associativity, int numSets, Policy<V> evictionPolicy){
        return new NWayCache<>(associativity, numSets, evictionPolicy);
    }
}
