package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int i = key == null ? 0 : indexFor(hash(key.hashCode()));
        boolean rsl = table[i] == null;
        if (rsl) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int i = entry.key == null ? 0 : indexFor(hash(entry.key.hashCode()));
                newTable[i] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int i = key == null ? 0 : indexFor(hash(key.hashCode()));
        MapEntry<K, V> e = table[i];
        V rsl = null;
        if (e != null && Objects.hashCode(key) == Objects.hashCode(e.key) && Objects.equals(key, e.key)) {
            rsl = e.value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int i = key == null ? 0 : indexFor(hash(key.hashCode()));
        MapEntry<K, V> e = table[i];
        boolean rsl = false;
        if (e != null && Objects.hashCode(key) == Objects.hashCode(e.key) && Objects.equals(key, e.key)) {
            table[i] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int cursor;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
