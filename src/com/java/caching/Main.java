package com.java.caching;

import com.java.caching.impl.LRUPolicy;

public class Main {
    public static void main(String [] args){
        Cache<String, String> cache = CacheFactory.newCache(1,3, new LRUPolicy<>());
        cache.put("a", "b");
        cache.get("a");

        cache = CacheFactory.newCache(1,3, new CustomCachePolicy<>());

        cache.put("a", "b");
        cache.get("a");

    }
}
