package com.algomonster.ads;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K,V> extends LinkedHashMap<K,V> {

    int size = 0, capacity = 0;
    public LruCache(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    @Override
    public V put(K key, V value) {
        super.put(key, value);
        size++;
        return value;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return (size >= capacity);
    }

    public static void main(String[] args) {
        LruCache<Integer,Integer> cache = new LruCache<>(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
