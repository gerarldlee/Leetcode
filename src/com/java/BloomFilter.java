package com.java;

/**
 *
 * A bit array is an extremely space efficient data structure that has each position in the array set to either 1 or 0
 *
 * A bloom filter is a set of k hash function which we hash incoming values.
 *
 * A query will hash incoming values and check if it exist in the bit array
 *
 * A bloom filter can have a positive or a false positive. This is very efficient if we wanted to fitler out negative
 * values.
 *
 * Time:
 * insert - o(k)
 * search - o(k) - k hash function
 *
 * Space:
 * o(m) - bits
 */
public class BloomFilter {

    private final BitSet bitSet;      // the data structure that contains all data in a BloomFilter

    public BloomFilter() {
        this.bitSet = new BitSet(10);
    }

    /**
     * Adds key into the bitSet
     */
    public void add(String key) {
        int hash = Math.abs(BloomFilter.HashUtil.hashCode(key));
        if (!bitSet.contains(hash)) {
            bitSet.add(hash);
        }
    }

    /**
     * Checks if the bitSet contains a hash
     */
    public boolean contains(String key) {
        int hash = Math.abs(BloomFilter.HashUtil.hashCode(key));
        return bitSet.contains(hash);
    }

    /**
     * BitSet data structure
     */
    class BitSet {

        private final int m;  // the number of bits
        private final boolean[] bits;     // the data itself, represented by boolean

        public BitSet(int m) {
            this.m = m;
            this.bits = new boolean[m];
        }

        /**
         * Adds a hash into this bitSet, mod by the total number of bits
         */
        public void add(int hash) {
            bits[hash % m] = true;
        }

        /**
         * Checks if this bitSet contains a hash
         */
        public boolean contains(int hash) {
            return bits[hash % m];
        }
    }

    /**
     * Util for generating hash code
     */
    static class HashUtil {
        public static int hashCode(String key) {
            return key.hashCode();
        }
    }


    public static void main(String[] argv) {

        BloomFilter bloom = new BloomFilter();
        bloom.add("gerard");
        bloom.add("gee");
        bloom.add("gerar");

        System.out.println(bloom.contains("gerard"));
        System.out.println(bloom.contains("gerards"));
        System.out.println(bloom.contains("gerar"));
        System.out.println(bloom.contains("g"));
    }
}
